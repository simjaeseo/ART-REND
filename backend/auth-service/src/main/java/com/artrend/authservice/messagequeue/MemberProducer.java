package com.artrend.authservice.messagequeue;


import com.artrend.authservice.dto.GoogleSignupDto;
import com.artrend.authservice.dto.kafka.Field;
import com.artrend.authservice.dto.kafka.KafkaMemberDto;
import com.artrend.authservice.dto.kafka.MemberPayload;
import com.artrend.authservice.dto.kafka.Schema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberProducer {


    private final KafkaTemplate<String, String> kafkaTemplate;

    List<Field> fields = Arrays.asList(
            new Field("string", true, "name"),
            new Field("string", true, "nickname"),
            new Field("String", true, "kakao_provider"),
            new Field("String", true, "kakao_provider_id"),
            new Field("String", true, "google_provider"),
            new Field("String", true, "google_provider_id")
    );

    Schema schema = Schema.builder()
            .type("struct")
            .fields(fields)
            .optional(false)
            .name("members")
            .build();

    public GoogleSignupDto send(String topic, GoogleSignupDto googleSignupDto) {

        MemberPayload payload = MemberPayload.builder()
                .name(googleSignupDto.getName())
                .google_provider(googleSignupDto.getProvider())
                .google_provider_id(googleSignupDto.getProviderId())
                .di(googleSignupDto.getDi())
                .build();

        KafkaMemberDto kafkaOrderDto = new KafkaMemberDto(schema, payload);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(kafkaOrderDto);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Order Producer sent data from the Order microservice: " + kafkaOrderDto);

        return googleSignupDto;
    }
}