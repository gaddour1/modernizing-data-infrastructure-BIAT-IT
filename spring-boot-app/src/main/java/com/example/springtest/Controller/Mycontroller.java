package com.example.springtest.Controller;

import com.example.springtest.Service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class Mycontroller {

    private final KafkaProducerService producer;

    // Endpoint POST pour envoyer un message dynamique
    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        producer.send(message);
        return "Message envoyé: " + message;
    }
}
