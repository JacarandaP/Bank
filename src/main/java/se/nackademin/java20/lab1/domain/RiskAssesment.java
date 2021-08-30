package se.nackademin.java20.lab1.domain;

import org.springframework.web.client.RestTemplate;

/**
 * Created by Jacaranda Perez
 * Date: 2021-08-30
 * Project: lab2
 */

public interface RiskAssesment {

    boolean isItApproved(String userId);

}
