package uni.pu.fmi.CarManagementAPI.service;

import uni.pu.fmi.CarManagementAPI.dto.request.CreateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.MonthlyRequestsReportDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageDailyAvailabilityReportDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseGarageDTO;

import java.util.List;

public interface GarageService {
    ResponseGarageDTO addGarage(CreateGarageDTO createGarageDTO);

    ResponseGarageDTO deleteGarageById(Long id);

    ResponseGarageDTO getGarageById(Long id);

    ResponseGarageDTO updateGarage(Long id, CreateGarageDTO createGarageDTO);

    List<ResponseGarageDTO> getAllGarages();

    List<GarageDailyAvailabilityReportDTO> dailyAvailabilityReport(MonthlyRequestsReportDTO monthlyRequestsReportDTO);

    List<ResponseGarageDTO> getGaragesByCity(String city);
}
