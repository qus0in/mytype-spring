package org.example.concertbooking.model.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.concertbooking.model.dto.ConcertDTO;
import org.example.concertbooking.model.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConcertRepository implements SupabaseRepository<ConcertDTO> {
    final private String tableName = "CONCERT";

    public void save(ConcertDTO concertDTO) throws Exception {
        System.out.println("Saving concert: " + concertDTO);
        save(concertDTO, tableName);
    }

    public List<ConcertDTO> findAll() throws Exception {
        String responseJson = findAll(tableName);
        return objectMapper.readValue(responseJson, new TypeReference<>() {});
    }

    public ConcertDTO findById(String concertId) throws Exception {
        String responseJson = findById(concertId,
                tableName, "concert_id");
        List<ConcertDTO> concertList = objectMapper.readValue(responseJson, new TypeReference<>(){});
        if (!concertList.isEmpty()) {
            return concertList.get(0);
        }
        return null;
    }
}
