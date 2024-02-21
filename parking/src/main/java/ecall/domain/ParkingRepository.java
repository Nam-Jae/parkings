package ecall.domain;

import ecall.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "parkings", path = "parkings")
public interface ParkingRepository
    extends PagingAndSortingRepository<Parking, Long> {}
