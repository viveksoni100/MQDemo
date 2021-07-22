package com.mq.demo.consumer;

import com.mq.demo.config.MQConfig;
import com.mq.demo.dto.VaccinatedCitizen;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author viveksoni100
 */
@Component
public class VaccineQueueListener {

    @RabbitListener(queues = MQConfig.QUEUE)
    public void consumeMessageFromQueue(VaccinatedCitizen vaccinatedCitizen) {
        System.out.println("Congratulations " + vaccinatedCitizen.getCitizen().getName() +
                " you are jabbed with " + vaccinatedCitizen.getVaccine().getName() + " successfully!");
    }

}
