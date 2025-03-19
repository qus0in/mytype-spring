package org.example.concertbooking.model.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.concertbooking.model.dto.TicketDTO;
import org.example.concertbooking.model.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepository implements SupabaseRepository<TicketDTO> {
    final private String tableName = "TICKET";

    public void save(TicketDTO ticketDTO) throws Exception {
        System.out.println("Saving ticket: " + ticketDTO);
        save(ticketDTO, tableName);
    }

    public List<TicketDTO> findAll() throws Exception {
        String responseJson = findAll(tableName);
        return objectMapper.readValue(responseJson, new TypeReference<>() {});
    }

    public TicketDTO findById(String ticketId) throws Exception {
        String responseJson = findById(ticketId,
                tableName, "ticker_id");
        return objectMapper.readValue(responseJson, new TypeReference<>() {});
    }
}
