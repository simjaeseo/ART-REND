package com.artrend.kafkaservice.messagequeue;


import com.artrend.kafkaservice.dto.kafka.Field;
import com.artrend.kafkaservice.dto.kafka.KafkaMemberLogDto;
import com.artrend.kafkaservice.dto.kafka.Schema;
import com.artrend.kafkaservice.dto.kafka.payload.MemberLogPayload;
import com.artrend.kafkaservice.dto.request.MemberLogRequest;
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
public class MemberLogProducer {


    private final KafkaTemplate<String, String> kafkaTemplate;

    List<Field> fields = Arrays.asList(
            new Field("int64", true, "member_id"),
            new Field("int64", true, "painting_id"),
            new Field("int64", true, "click_count"),
            new Field("int64", true, "zoom_count"),
            new Field("int64", true, "change_count"),
            new Field("string", true, "in_time"),
            new Field("string", true, "out_time")
    );

    Schema schema = Schema.builder()
            .type("struct")
            .fields(fields)
            .optional(false)
            .name("member_log")
            .build();

    // GoogleSignupDto -> insert할 데이터를 담고 있는 dto ---> 그냥 payload 객체로 받아도  상관은 없을듯한데..
    public void memberLogSend(String topic, MemberLogRequest memberLogRequest) {

//         Long clickCount;
//         Long zoomCount;
//         Long changeCount;
//         String inTime;
//         String outTime;


        // 카프카 메세지에 실제 담으려는 payload 객체
        MemberLogPayload payload = MemberLogPayload.builder()
                .member_id(memberLogRequest.getMemberId())
                .painting_id(memberLogRequest.getPaintingId())
                .click_count(memberLogRequest.getClickCount())
                .zoom_count(memberLogRequest.getZoomCount())
                .change_count(memberLogRequest.getChangeCount())
                .in_time(memberLogRequest.getInTime())
                .out_time(memberLogRequest.getOutTime())
                .build();

        // 실제 토픽에 메세지를 보내는 객체 (스키마 + 페이로드) / json 타입으로 변환해야함
        KafkaMemberLogDto kafkaMemberLogDto = new KafkaMemberLogDto(schema, payload);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(kafkaMemberLogDto);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Order Producer sent data from the Order microservice: " + kafkaMemberLogDto);

//        return googleSignupDto;
    }

}