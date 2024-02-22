package ecall.domain;

import ecall.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class Issued extends AbstractEvent {

    private Long id;
    private String customerId;
    private String reservationId;
}