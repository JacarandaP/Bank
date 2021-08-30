package se.nackademin.java20.lab1.Risk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Jacaranda Perez
 * Date: 2021-08-30
 * Project: lab2
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskAssesmentDto {

    private final boolean result;

    @JsonCreator
    public RiskAssesmentDto(@JsonProperty("pass")boolean result){
        this.result = result;
    }

    public boolean getResult(){
        return result;
    }

}
