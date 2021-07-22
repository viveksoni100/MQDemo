package com.mq.demo.publisher;

import com.mq.demo.config.MQConfig;
import com.mq.demo.dto.Citizen;
import com.mq.demo.dto.VaccinatedCitizen;
import com.mq.demo.dto.Vaccine;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author viveksoni100
 */
@RestController
public class VaccineDrive {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    @Qualifier("vaccineQueue")
    private Queue VaccineQueue;

    @GetMapping("vaccinateCitizen")
    public String vaccinateCitizen() {

        Citizen citizen = new Citizen();
        citizen.setName("Vivek Soni");
        citizen.setAdharID(UUID.randomUUID().toString());

        Vaccine vaccine = new Vaccine();
        vaccine.setName("Pfizer");
        vaccine.setDose(1.5f);

        VaccinatedCitizen vaccinatedCitizen = new VaccinatedCitizen();
        vaccinatedCitizen.setCitizen(citizen);
        vaccinatedCitizen.setVaccine(vaccine);

        // sending message to out queue
        template.convertAndSend(MQConfig.EXCHANGE, VaccineQueue.getName(), vaccinatedCitizen);

        return "Success!";

    }

}
