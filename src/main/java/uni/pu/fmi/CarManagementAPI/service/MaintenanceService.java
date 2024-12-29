package uni.pu.fmi.CarManagementAPI.service;

import uni.pu.fmi.CarManagementAPI.dto.request.CreateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateMaintenanceDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.UpdateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.UpdateMaintenanceDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageDailyAvailabilityReportDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.MonthlyRequestsReportDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseMaintenanceDTO;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface MaintenanceService {
    ResponseMaintenanceDTO createMaintenance(CreateMaintenanceDTO maintenanceGarageDTO);

    ResponseMaintenanceDTO deleteMaintenanceById(Long id);

    ResponseMaintenanceDTO getMaintenanceById(Long id);

    ResponseMaintenanceDTO updateMaintenance(Long id, UpdateMaintenanceDTO updateMaintenanceDTO);

    //List<ResponseMaintenanceDTO> getAllMaintenances();

    List<ResponseMaintenanceDTO> getAllMaintenances(Long carId, Long garageId, LocalDate startDate, LocalDate endDate);

    List<MonthlyRequestsReportDTO> getMonthlyRequestsReport (Long garageId, YearMonth startMonth, YearMonth endMonth);
}
