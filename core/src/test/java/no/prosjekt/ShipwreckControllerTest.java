package no.prosjekt;


import no.prosjekt.controller.ShipwreckController;
import no.prosjekt.model.Shipwreck;
import no.prosjekt.repository.ShipwreckRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Description;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ShipwreckControllerTest {

    @InjectMocks
    private ShipwreckController shipwreckController;

    @Mock
    private ShipwreckRepository shipwreckRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShipwreckGet(){
        Shipwreck shipwreck = new Shipwreck();
        shipwreck.setId(1L);
        Mockito.when(shipwreckRepository.findById(1L)).thenReturn(java.util.Optional.of(shipwreck));

        Shipwreck shipwreckUnderTest = shipwreckController.get(1L);

        Mockito.verify(shipwreckRepository).findById(1L);

        //assertEquals(1L, shipwreckUnderTest.getId().longValue());
        // Hamcrest variant!
        assertThat(shipwreckUnderTest.getId(), is(1L));
    }

    @Test
    @Description("Disse gir ikke mening og tester ingenting. Bare leking med mockito.")
    public void testShipwreckCreate() {
        Shipwreck shipwreck = new Shipwreck();
        shipwreck.setId(1L);
        shipwreck.setDepth(100);
        shipwreck.setLatitude(10.2);

        Mockito.when(shipwreckRepository.saveAndFlush(shipwreck)).thenReturn(shipwreck);

        Shipwreck shipwreckUnderTest = shipwreckController.create(shipwreck);
        Mockito.verify(shipwreckRepository).saveAndFlush(shipwreck);

        assertEquals(shipwreck, shipwreckUnderTest);
    }

    @Test
    @Description("Disse gir ikke mening og tester ingenting. Bare leking med mockito.")
    public void testShipwreckUpdateWithMockObject() {
        Shipwreck shipwreckOld = new Shipwreck();
        shipwreckOld.setId(1L);
        Mockito.when(shipwreckRepository.saveAndFlush(shipwreckOld)).thenReturn(shipwreckOld);

        Shipwreck shipwreckOldReturned = shipwreckController.create(shipwreckOld);
        Mockito.verify(shipwreckRepository).saveAndFlush(shipwreckOld);

        assertEquals(shipwreckOld, shipwreckOldReturned);

        Shipwreck shipwreck = new Shipwreck();
        shipwreck.setId(1L);
        shipwreck.setDepth(100);
        shipwreck.setLatitude(10.2);

        Mockito.when(shipwreckRepository.findById(1L)).thenReturn(java.util.Optional.of(shipwreck));
        Mockito.when(shipwreckRepository.saveAndFlush(shipwreck)).thenReturn(shipwreck);

        Shipwreck shipwreckUnderTest = shipwreckController.update(1L, shipwreck);
        Mockito.verify(shipwreckRepository).saveAndFlush(shipwreck);
        assertEquals(shipwreck, shipwreckUnderTest);
    }
}
