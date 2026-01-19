package com.algaworks.algadelivery.couriermanagement.domain.model;

import com.algaworks.algadelivery.couriermanagement.domain.model.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {
    @Test
    public void shouldChangeStatusToPlaced(){
        Delivery delivery = Delivery.draft();
        delivery.editPreparationDetails(createValidPreparationDetails());
        delivery.place();
        assertEquals(DeliveryStatus.WAITING_FOR_COURIER,delivery.getStatus());
        assertNotNull(delivery.getPlacedAt());
    }
    @Test
    public void shouldNotPlace(){
        Delivery delivery = Delivery.draft();

        assertThrows(DomainException.class,() -> {
            delivery.place();
        });
        assertEquals(DeliveryStatus.DRAFT,delivery.getStatus());
        assertNull(delivery.getPlacedAt());
    }

    private Delivery.PreparationDetails createValidPreparationDetails() {
        ContactPoint sender = ContactPoint.builder()
                .zipCode("00000-000")
                .street("Rua SP")
                .number("100")
                .complement("Sala 401")
                .phone("(11) 90000-1234")
                .name("João Silva")
                .build();
        ContactPoint recipient = ContactPoint.builder()
                .zipCode("12331-342")
                .street("Rua BR")
                .number("50")
                .complement("Apt-543")
                .phone("(43) 90000-1234")
                .name("Pedro")
                .build();


        return Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal("15.00"))
                .courierPayout(new BigDecimal("5.00"))
                .expectedDeliveryTime(Duration.ofHours(5))
                .build();
    }

}