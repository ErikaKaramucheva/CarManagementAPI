package uni.pu.fmi.CarManagementAPI.service;

import uni.pu.fmi.CarManagementAPI.dto.request.CreateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.GarageAvailabilityDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.UpdateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageAvailabilityResponse;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageResponse;

import java.util.List;

public interface GarageService {
    GarageResponse addGarage(CreateGarageDTO createGarageDTO);
    GarageResponse deleteGarageById(Long id);
    GarageResponse getGarageById(Long id);
    GarageResponse updateGarage(Long id,CreateGarageDTO createGarageDTO);
    List<GarageResponse> getAllGarages();
    List<GarageAvailabilityResponse> dailyAvailabilityReport(GarageAvailabilityDTO garageAvailabilityDTO);
}
