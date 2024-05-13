package com.example.chat.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private String typeMessage; // ID, PUBLIC_KEY, TEXT
    private long clientId; // От кого (айди клиента)
    private byte[] publicKey; // public Key
    private byte[] text; // encrypted text

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception ex) {
            log.error("Error while transform to json message", ex);
        }

        return "";
    }
}
