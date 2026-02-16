# 📘 Documentation -- Spring Boot Kafka Setup

## 1️⃣ Installation et configuration IntelliJ IDEA

-   IntelliJ IDEA 2025.3.2 installé\
-   Projet Maven Spring Boot créé\
-   Java 17 utilisé

------------------------------------------------------------------------

## 2️⃣ Structure du projet

    spring-test/
    │
    ├─ .mvn
    ├─ src
    │   └─ main
    │       └─ java/com/example/springtest/
    │           ├─ Config/
    │           ├─ Controller/
    │           │   └─ Mycontroller.java
    │           ├─ Service/
    │           │   ├─ KafkaConsumerService.java
    │           │   └─ KafkaProducerService.java
    │           └─ SpringTestApplication.java
    │
    └─ src/main/resources/
        ├─ application.properties
        ├─ static/
        └─ templates/

------------------------------------------------------------------------

## 3️⃣ Dépendances clés dans le pom.xml

-   **spring-boot-starter-kafka** → pour Kafka\
-   **spring-boot-starter-webmvc** → pour exposer endpoints REST\
-   **springdoc-openapi-starter-webmvc-ui** → pour documentation Swagger
    si nécessaire\
-   **lombok** → simplifie le code (constructeurs, getters/setters)

------------------------------------------------------------------------

## 4️⃣ Configuration Kafka (application.properties)

``` properties
spring.application.name=spring-test
spring.kafka.bootstrap-servers=localhost:9092

# Consumer
spring.kafka.consumer.group-id=test-group
spring.kafka.consumer.auto-offset-reset=earliest

# Port de l'application
server.port=8086
```

------------------------------------------------------------------------

## 5️⃣ Services Kafka

### KafkaProducerService.java

``` java
@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {
        kafkaTemplate.send("test-topic", message);
        System.out.println("Message envoyé: " + message);
    }
}
```

### KafkaConsumerService.java

``` java
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void listen(String message) {
        System.out.println("Message reçu: " + message);
    }
}
```

------------------------------------------------------------------------

## 6️⃣ Controller REST

``` java
@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class Mycontroller {

    private final KafkaProducerService producer;

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        producer.send(message);
        return "Message envoyé: " + message;
    }
}
```

**Endpoint :** `POST http://localhost:8086/kafka/send`

➡️ Envoie un message dans le topic Kafka **test-topic**.

------------------------------------------------------------------------

