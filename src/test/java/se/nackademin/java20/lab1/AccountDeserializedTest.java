package se.nackademin.java20.lab1;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import se.nackademin.java20.lab1.domain.AccountDeserialized;
import se.nackademin.java20.lab1.Risk.RiskAssesmentDto;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jacaranda Perez
 * Date: 2021-08-26
 * Project: lab2
 */

public class AccountDeserializedTest {


    private final String json = "{\"balance\":\"0\", \"holder\":\"dan\"}";
    private final String resultRiskAssesment = "{\"result\":true}";

    @Test
    void deserialize() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        AccountDeserialized accountDeserialized = objectMapper.readValue(json, AccountDeserialized.class);

        assertEquals(accountDeserialized.getHolder(), "dan");
    }

    @Test
    void serialize() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        AccountDeserialized accountDeserialized = new AccountDeserialized("0", "Blanca");
        objectMapper.writeValue(new File("accountDeserialized.json"), accountDeserialized);
        String accountSerialised = objectMapper.writeValueAsString(accountDeserialized);

        assertEquals(accountSerialised, "{\"balance\":\"0\",\"holder\":\"Blanca\"}");
    }

    @Test
    void rest() {
        ResponseEntity<String> forEntity = new
                RestTemplate().getForEntity("https://data.riksdagen.se/anforandelista/?utformat=json&anftyp=Nej&sz=10", String.class);
        System.out.println(forEntity.getStatusCode());
        System.out.println(forEntity.getBody());
    }

    @Test
    void restDeserialized() {
        ResponseEntity<String> forEntity = new
                RestTemplate().getForEntity("http://localhost:8082/risk/dan", String.class);
        System.out.println(forEntity.getStatusCode());
        System.out.println(forEntity.getBody());
    }

    @Test
    void getRest(){
        ResponseEntity<RiskAssesmentDto> forEntity = new
                RestTemplate().getForEntity("http://localhost:8082/risk/dan", RiskAssesmentDto.class);
        System.out.println(forEntity.getStatusCode());
        System.out.println(forEntity.getBody().getResult());

    }

    @Test
    void deserialization() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RiskAssesmentDto riskAssesmentDto = new RiskAssesmentDto(true);

        String jsonObject = objectMapper.writeValueAsString(riskAssesmentDto);
        System.out.println(jsonObject);
        Assertions.assertEquals(resultRiskAssesment, jsonObject);
    }

    @Ignore
    @Test
    void restSerialized() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();

        RiskAssesmentDto riskAssesmentDto = new RiskAssesmentDto(false);
        objectMapper.writeValue(new File("riskAssesmentResult.json"), RiskAssesmentDto.class);
        String result = objectMapper.writeValueAsString(riskAssesmentDto);
        assertEquals(riskAssesmentDto,"{\"false\"}" );
    }



}
