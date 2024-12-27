package uni.pu.fmi.CarManagementAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseGarageDTO;
import uni.pu.fmi.CarManagementAPI.service.GarageService;

import java.util.List;

@RestController
@RequestMapping("/garages")
public class GarageController {
    @Autowired
    private GarageService garageService;

    @PostMapping
    public ResponseEntity<ResponseGarageDTO> createGarage(@RequestBody CreateGarageDTO garageDTO) {
        ResponseGarageDTO garage = garageService.addGarage(garageDTO);
        return ResponseEntity.ok(garage);
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseGarageDTO>> getGarage() {
        List<ResponseGarageDTO> resp = garageService.getAllGarages();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGarageDTO> updateGarage(@RequestHeader Long id,
                                                          @RequestBody CreateGarageDTO createGarageDTO) {
        ResponseGarageDTO garage = garageService.updateGarage(id, createGarageDTO);
        return ResponseEntity.ok(garage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGarageDTO> getGarageById(@RequestHeader Long id) {
        ResponseGarageDTO garage = garageService.getGarageById(id);
        return ResponseEntity.ok(garage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseGarageDTO> deleteGarage(@RequestHeader Long id) {
        ResponseGarageDTO garage = garageService.deleteGarageById(id);
        return ResponseEntity.ok(garage);
    }
}
