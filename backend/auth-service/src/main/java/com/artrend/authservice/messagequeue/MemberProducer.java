package com.artrend.authservice.messagequeue;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberProducer {


//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    List<Field> fields = Arrays.asList(
//            new Field("Long", true, "id"),
//            new Field("string", true, "name"),
//            new Field("string", true, "nickname"),
//            new Field("String", true, "kakao_provider"),
//            new Field("String", true, "kakao_provider_id"),
//            new Field("String", true, "google_provider"),
//            new Field("String", true, "google_provider_id")
//    );
//
//    Schema schema = Schema.builder()
//            .type("struct")
//            .fields(fields)
//            .optional(false)
//            .name("orders")
//            .build();
//
//    public KafkaMemberDto send(String topic, KafkaMemberDto orderDto) {
//
//        MemberPayload payload = MemberPayload.builder()
//                .order_id(orderDto.getOrderId())
//                .user_id(orderDto.getUserId())
//                .product_id(orderDto.getProductId())
//                .qty(orderDto.getQty())
//                .unit_price(orderDto.getUnitPrice())
//                .total_price(orderDto.getTotalPrice())
//                .build();
//
//        KafkaOrderDto kafkaOrderDto = new KafkaOrderDto(schema, payload);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonInString = "";
//        try {
//            jsonInString = mapper.writeValueAsString(kafkaOrderDto);
//        } catch (JsonProcessingException ex) {
//            ex.printStackTrace();
//        }
//
//        kafkaTemplate.send(topic, jsonInString);
//        log.info("Order Producer sent data from the Order microservice: " + kafkaOrderDto);
//
//        return orderDto;
//    }
}