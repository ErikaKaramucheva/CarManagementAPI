package uni.pu.fmi.CarManagementAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageResponse;
import uni.pu.fmi.CarManagementAPI.service.GarageService;

import java.util.List;

@RestController
@RequestMapping("/garages")
public class GarageController {
    @Autowired
    private GarageService garageService;

    @PostMapping
    public ResponseEntity<GarageResponse> createGarage(@RequestBody CreateGarageDTO garageDTO){
        GarageResponse garage=garageService.addGarage(garageDTO);
        return ResponseEntity.ok(garage);
    }
    @GetMapping("/")
    public ResponseEntity<List<GarageResponse>> getGarage() {
        List<GarageResponse> resp = garageService.getAllGarages();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<GarageResponse> updateGarage(@RequestHeader Long id,
                                                       @RequestBody CreateGarageDTO createGarageDTO){
        GarageResponse garage=garageService.updateGarage(id,createGarageDTO);
        return ResponseEntity.ok(garage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GarageResponse> getGarageById(@RequestHeader Long id){
        GarageResponse garage=garageService.getGarageById(id);
        return ResponseEntity.ok(garage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GarageResponse> deleteGarage(@RequestHeader Long id){
        GarageResponse garage=garageService.deleteGarageById(id);
        return ResponseEntity.ok(garage);
    }
}
