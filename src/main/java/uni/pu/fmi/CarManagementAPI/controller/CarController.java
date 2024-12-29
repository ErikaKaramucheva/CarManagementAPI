package uni.pu.fmi.CarManagementAPI.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.UpdateCarDTO;
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
    public ResponseEntity<List<ResponseCarDTO>> getCars(@RequestParam(required = false) String carMake,
                                                        @RequestParam(required = false) Long garageId,
                                                        @RequestParam(required = false) String city,
                                                        @RequestParam(required = false) Integer fromYear,
                                                        @RequestParam(required = false) Integer toYear) {
         List<ResponseCarDTO> resp = carService.getAllCars(carMake, garageId, city, fromYear, toYear);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> updateCar(@PathVariable Long id,
                                                    @Valid @RequestBody UpdateCarDTO updateCarDTO) {
        ResponseCarDTO car = carService.updateCar(id, updateCarDTO);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> getCarById(@PathVariable Long id) {
        ResponseCarDTO car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCarDTO> deleteCar(@PathVariable Long id) {
        ResponseCarDTO car = carService.deleteCarById(id);
        return ResponseEntity.ok(car);
    }
}
