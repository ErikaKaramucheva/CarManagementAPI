package uni.pu.fmi.CarManagementAPI.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.UpdateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageDailyAvailabilityReportDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseGarageDTO;
import uni.pu.fmi.CarManagementAPI.service.GarageService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/garages")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "400", content = {@Content()}),
        @ApiResponse(responseCode = "404",content = {@Content()})
})
//@CrossOrigin(origins = "http://localhost:3000")
public class GarageController {
    @Autowired
    private GarageService garageService;

    @PostMapping
    public ResponseEntity<ResponseGarageDTO> createGarage(@Valid
                                                              @RequestBody CreateGarageDTO garageDTO) {
        ResponseGarageDTO garage = garageService.addGarage(garageDTO);
        return ResponseEntity.ok(garage);
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseGarageDTO>> getAllGarages(@RequestParam(required = false) String city) {
        List<ResponseGarageDTO> resp = garageService.getGaragesByCity(city);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGarageDTO> updateGarage(@PathVariable Long id,
                                                          @Valid @RequestBody UpdateGarageDTO updateGarageDTO) {
        ResponseGarageDTO garage = garageService.updateGarage(id, updateGarageDTO);
        return ResponseEntity.ok(garage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGarageDTO> getGarageById(@PathVariable Long id) {
        ResponseGarageDTO garage = garageService.getGarageById(id);
        return ResponseEntity.ok(garage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseGarageDTO> deleteGarage(@PathVariable Long id) {
        ResponseGarageDTO garage = garageService.deleteGarageById(id);
        return ResponseEntity.ok(garage);
    }

    @GetMapping("/dailyAvailabilityReport")
    public ResponseEntity<List<GarageDailyAvailabilityReportDTO>> dailyAvailability(@RequestParam Long garageId,
                                                                              @Valid @RequestParam LocalDate startDate,
                                                                              @Valid @RequestParam LocalDate endDate){
        List<GarageDailyAvailabilityReportDTO> garages =garageService.dailyAvailabilityReport(garageId,startDate,endDate);
        return new ResponseEntity<>(garages, HttpStatus.OK);
    }
}
