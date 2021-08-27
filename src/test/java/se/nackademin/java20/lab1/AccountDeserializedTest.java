package se.nackademin.java20.lab1;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import se.nackademin.java20.lab1.domain.Account;
import se.nackademin.java20.lab1.domain.AccountDeserialized;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jacaranda Perez
 * Date: 2021-08-26
 * Project: lab2
 */

public class AccountDeserializedTest {


    private final String json = "{\"balance\":\"0\", \"holder\":\"dan\"}";

    @Test
    void deserialize() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        AccountDeserialized accountDeserialized = objectMapper.readValue(json, AccountDeserialized.class);

        assertEquals(accountDeserialized.getHolder(), "dan");
    }

    @Ignore
    @Test
    void serialize(){
        ObjectMapper objectMapper = new ObjectMapper();

        AccountDeserialized accountDeserialized = new AccountDeserialized();
        accountDeserialized.setBalance("0");
        accountDeserialized.setHolder("rosa");

        //String jsonObject = objectMapper.writeValueAsString(dan); continue
    }

    @Test
    void rest() {
        ResponseEntity<String> forEntity = new
                RestTemplate().getForEntity("https://data.riksdagen.se/anforandelista/?utformat=json&anftyp=Nej&sz=10", String.class);
        System.out.println(forEntity.getStatusCode());
        System.out.println(forEntity.getBody());
    }



}
