package ecall.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "parking", url = "${api.url.parking}")
public interface ParkingService {
    @RequestMapping(method = RequestMethod.GET, path = "/parkings/{id}")
    public Parking getParking(@PathVariable("id") Long id);
}
