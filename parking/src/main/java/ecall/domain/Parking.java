package ecall.domain;

import ecall.ParkingApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Parking_table")
@Data
//<<< DDD / Aggregate Root
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long parkingSpot;

    @PostPersist
    public void onPostPersist() {}

    public static ParkingRepository repository() {
        ParkingRepository parkingRepository = ParkingApplication.applicationContext.getBean(
            ParkingRepository.class
        );
        return parkingRepository;
    }

    //<<< Clean Arch / Port Method
    public static void decreaseParkingSpot(Reserved reserved) {
        repository().findById(Long.valueOf(reserved.getParkingId())).ifPresent(parking->{
            
            parking.setParkingSpot(parking.getParkingSpot() - 1); 
            repository().save(parking);
         });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void increaseParkingSpot(Canceled canceled) {
        repository().findById(Long.valueOf(canceled.getParkingId())).ifPresent(parking->{
            
            parking.setParkingSpot(parking.getParkingSpot() + 1); 
            repository().save(parking);
         });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
