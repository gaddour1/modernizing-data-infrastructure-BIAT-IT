package com.example.springtest.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service   // ← Spring va gérer cette classe automatiquement
@RequiredArgsConstructor
public class KafkaProducerService {


    private final KafkaTemplate<String, String> kafkaTemplate; // Injecté par Spring

    public void send(String message) {
        kafkaTemplate.send("test-topic", message);  // envoie le message au topic
        System.out.println("Message envoyé: " + message);
    }
}