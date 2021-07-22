package com.mq.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author viveksoni100
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccinatedCitizen {

    private Citizen citizen;
    private Vaccine vaccine;
}
