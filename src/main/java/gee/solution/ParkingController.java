package gee.solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    public class Controller {

        private final Service service;

        @Autowired
        public Controller(Service service) {
            this.service = service;
        }

        @PostMapping
        public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
            service.sendSms(smsRequest);
        }
    }

    @Autowired
    ParkingRepository parkingRepository;


    @GetMapping("")
    public List<Parking> getAll() {
        return parkingRepository.getAll();
    }

    @GetMapping("/{id}")
    public Parking getById(@PathVariable("id") int id) {
        return parkingRepository.getById(id);
}

    @PostMapping("")
    public int add(@RequestBody List<Parking> parkings) {
        return parkingRepository.save(parkings);
    }
    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Parking updatedParking) {
        Parking parking = parkingRepository.getById(id);

        if (parking != null) {
            parking.setName(updatedParking.getName());
            parking.setNumber(updatedParking.getNumber());

            parkingRepository.update(parking);

            return 1;
        }
        else {
            return -1;
        }
    }
    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Parking updatedParking) {
        Parking parking = parkingRepository.getById(id);

        if (parking != null){
            if (updatedParking.getName() != null) parking.setName(updatedParking.getName());
            if (updatedParking.getNumber() > 0) parking.setNumber(updatedParking.getNumber());

            parkingRepository.update(parking);
            return 1;
        } else {
            return -1;
      }
    }
    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return parkingRepository.delete(id);
    }

}
