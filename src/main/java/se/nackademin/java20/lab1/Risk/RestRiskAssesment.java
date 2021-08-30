package se.nackademin.java20.lab1.Risk;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.nackademin.java20.lab1.domain.RiskAssesment;

/**
 * Created by Jacaranda Perez
 * Date: 2021-08-30
 * Project: lab2
 */

public class RestRiskAssesment implements RiskAssesment { //this is the adapter or most of it(RiskAssessmentInterface and Dto too)

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public RestRiskAssesment(RestTemplate restTemplate, String baseUrlUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrlUrl;
    }

    @Override
    public boolean isItApproved(String userId) {
        final ResponseEntity<RiskAssesmentDto> entity = restTemplate.getForEntity(baseUrl + "/risk/" + userId, RiskAssesmentDto.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody().getResult();
        }
        //annars
        throw new RuntimeException("Assessment could not be done");
    }

}