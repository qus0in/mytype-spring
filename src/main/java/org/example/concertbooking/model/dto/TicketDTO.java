package org.example.concertbooking.model.dto;

import java.math.BigDecimal;

public record TicketDTO(String ticket_id, String seat_number, BigDecimal price, String purchase_date, String concert_id, String user_id) {
}
