package ecall.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ecall.config.kafka.KafkaProcessor;
import ecall.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ParkingRepository parkingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Reserved'"
    )
    public void wheneverReserved_DecreaseParkingSpot(
        @Payload Reserved reserved
    ) {
        Reserved event = reserved;
        System.out.println(
            "\n\n##### listener DecreaseParkingSpot : " + reserved + "\n\n"
        );

        // Sample Logic //
        Parking.decreaseParkingSpot(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Canceled'"
    )
    public void wheneverCanceled_IncreaseParkingSpot(
        @Payload Canceled canceled
    ) {
        Canceled event = canceled;
        System.out.println(
            "\n\n##### listener IncreaseParkingSpot : " + canceled + "\n\n"
        );

        // Sample Logic //
        Parking.increaseParkingSpot(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
