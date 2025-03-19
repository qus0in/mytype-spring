package org.example.concertbooking.model.dto;

public record User(
        String user_id,
        String name,
        String email,
        String phone,
        String ticket_id) {
}
