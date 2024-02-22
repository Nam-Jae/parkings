package ecall.domain;

import ecall.CouponApplication;
import ecall.domain.Issued;
import ecall.domain.Retrieved;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Coupon_table")
@Data
//<<< DDD / Aggregate Root
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerId;

    private Long reservationId;

    @PostPersist
    public void onPostPersist() {
        Issued issued = new Issued(this);
        issued.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        Retrieved retrieved = new Retrieved(this);
        retrieved.publishAfterCommit();
    }

    public static CouponRepository repository() {
        CouponRepository couponRepository = CouponApplication.applicationContext.getBean(
            CouponRepository.class
        );
        return couponRepository;
    }

    //<<< Clean Arch / Port Method
    public static void addCouponList(Reserved reserved) {
        Coupon coupon = new Coupon();

        coupon.setReservationId(reserved.getId());
        coupon.setCustomerId(reserved.getCustomerId());
        repository().save(coupon);
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void deleteCouponList(Canceled canceled) {
        Retrieved retrieved = new Retrieved();
        retrieved.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
