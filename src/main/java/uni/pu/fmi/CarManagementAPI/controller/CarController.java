package uni.pu.fmi.CarManagementAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseCarDTO;
import uni.pu.fmi.CarManagementAPI.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<ResponseCarDTO> createCar(@RequestBody CreateCarDTO carDTO) {
        ResponseCarDTO car = carService.createCar(carDTO);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseCarDTO>> getCars() {
        List<ResponseCarDTO> resp = carService.getAllCars();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> updateCar(@RequestHeader Long id,
                                                    @RequestBody CreateCarDTO createCarDTO) {
        ResponseCarDTO car = carService.updateCar(id, createCarDTO);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> getCarById(@RequestHeader Long id) {
        ResponseCarDTO car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> deleteCar(@RequestHeader Long id) {
        ResponseCarDTO car = carService.deleteCarById(id);
        return ResponseEntity.ok(car);
    }
}
