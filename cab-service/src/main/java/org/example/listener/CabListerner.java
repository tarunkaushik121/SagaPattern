package org.example.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dtos.CommonStatus;
import org.example.entity.Cab;
import org.example.events.CabEvent;
import org.example.events.UpdateDriverStatusEvent;
import org.example.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CabListerner {

    @Autowired
    CabService cabService;


    private KafkaTemplate<String, UpdateDriverStatusEvent> updateDriverStatusEventKafkaTemplate;

    private final String ADD_CAB_EVENT="add-cab_event";

    @Autowired
    public CabListerner(CabService cabService){
        this.cabService=cabService;
        this.updateDriverStatusEventKafkaTemplate=updateDriverStatusEventKafkaTemplate;
    }


    @KafkaListener(topics = "add-cab-event",groupId = "driver-group")
    public void addCabDetails(String event) throws JsonProcessingException {
        CabEvent cabEvent=new ObjectMapper().readValue(event,CabEvent.class);

        boolean cabExist=cabService.existByRegistrationNumberAndCabStatus(cabEvent.getRegistrationNumber(), CommonStatus.SUCCESS);
        if(cabExist){
            //Update driver with failed status
            saveCabDetailsAndUpdateDriverEvent(cabEvent,CommonStatus.FAILED);
        }
            //update driver with success status
            saveCabDetailsAndUpdateDriverEvent(cabEvent,CommonStatus.SUCCESS);

        System.out.println(cabEvent);

    }

    private void saveCabDetailsAndUpdateDriverEvent(CabEvent cabEvent, CommonStatus commonStatus) {
        Cab cab=saveCabDetails(cabEvent,commonStatus);
        updateDriverEvent(cab, commonStatus);

    }

    private void updateDriverEvent(Cab cab, CommonStatus commonStatus) {
        UpdateDriverStatusEvent statusEvent=UpdateDriverStatusEvent.builder()
                .driverID(cab.getDriverId())
                .driverStatus(cab.getCabStatus())
                .build();
        updateDriverStatusEventKafkaTemplate.send("update-driver-event",statusEvent);
    }

    private Cab saveCabDetails(CabEvent cabEvent, CommonStatus commonStatus) {
        return cabService.saveCab(Cab.builder()
                .driverId(cabEvent.getDriverId())
                .registrationNumber(cabEvent.getRegistrationNumber())
                .cabType(cabEvent.getCabTypes())
                .cabStatus(commonStatus)
                .build());
    }
}
