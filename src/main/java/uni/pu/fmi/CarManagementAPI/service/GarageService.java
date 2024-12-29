package uni.pu.fmi.CarManagementAPI.service;

import uni.pu.fmi.CarManagementAPI.dto.request.CreateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.UpdateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.MonthlyRequestsReportDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageDailyAvailabilityReportDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseGarageDTO;

import java.time.LocalDate;
import java.util.List;

public interface GarageService {
    ResponseGarageDTO addGarage(CreateGarageDTO createGarageDTO);

    ResponseGarageDTO deleteGarageById(Long id);

    ResponseGarageDTO getGarageById(Long id);

    ResponseGarageDTO updateGarage(Long id, UpdateGarageDTO updateGarageDTO);

    List<GarageDailyAvailabilityReportDTO> dailyAvailabilityReport(Long garageId, LocalDate startDate, LocalDate endDate);

    List<ResponseGarageDTO> getGaragesByCity(String city);
}
