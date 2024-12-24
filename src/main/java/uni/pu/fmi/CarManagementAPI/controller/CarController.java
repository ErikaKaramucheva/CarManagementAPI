package uni.pu.fmi.CarManagementAPI.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.CarResponse;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageResponse;
import uni.pu.fmi.CarManagementAPI.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CreateCarDTO carDTO){
        CarResponse car=carService.createCar(carDTO);
        return ResponseEntity.ok(car);
    }
    @GetMapping("/")
    public ResponseEntity<List<CarResponse>> getCars() {
        List<CarResponse> resp = carService.getAllCars();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
