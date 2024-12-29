package uni.pu.fmi.CarManagementAPI.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateMaintenanceDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.UpdateMaintenanceDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.MonthlyRequestsReportDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseMaintenanceDTO;
import uni.pu.fmi.CarManagementAPI.service.Implementation.MaintenanceServiceImpl;
import uni.pu.fmi.CarManagementAPI.service.MaintenanceService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<ResponseMaintenanceDTO> createMaintenance(@Valid
                                                          @RequestBody CreateMaintenanceDTO maintenanceDTO) {
        ResponseMaintenanceDTO maintenance = maintenanceService.createMaintenance(maintenanceDTO);
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseMaintenanceDTO>> getMaintenance(@RequestParam(required = false) Long carId,
                                                                       @RequestParam(required=false) Long garageId,
                                                                       @RequestParam(required = false) LocalDate startDate,
                                                                       @RequestParam(required = false) LocalDate endDate) {
        List<ResponseMaintenanceDTO> resp = maintenanceService.getAllMaintenances(carId, garageId, startDate, endDate);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/monthlyRequestsReport")
    public ResponseEntity<List<MonthlyRequestsReportDTO>> getMonthlyRequestsReport(@RequestParam Long garageId,
                                                                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth startMonth,
                                                                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth endMonth) {
        List<MonthlyRequestsReportDTO> resp = maintenanceService.getMonthlyRequestsReport(garageId, startMonth, endMonth);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMaintenanceDTO> updateMaintenance(@PathVariable Long id,
                                                          @Valid @RequestBody UpdateMaintenanceDTO updateMaintenanceDTO) {
        ResponseMaintenanceDTO maintenance = maintenanceService.updateMaintenance(id, updateMaintenanceDTO);
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMaintenanceDTO> getMaintenanceById(@PathVariable Long id) {
        ResponseMaintenanceDTO maintenance = maintenanceService.getMaintenanceById(id);
        return ResponseEntity.ok(maintenance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMaintenanceDTO> deleteMaintenance(@PathVariable Long id) {
        ResponseMaintenanceDTO maintenance = maintenanceService.deleteMaintenanceById(id);
        return ResponseEntity.ok(maintenance);
    }
}
