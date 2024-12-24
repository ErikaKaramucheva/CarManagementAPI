package uni.pu.fmi.CarManagementAPI.service;

import org.springframework.stereotype.Service;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.GarageAvailabilityDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.UpdateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.CarResponse;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageAvailabilityResponse;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageResponse;

import java.util.List;

@Service
public interface CarService {
    CarResponse createCar (CreateCarDTO createCarDTO);
    CarResponse deleteCarById(Long id);
    CarResponse getCarById(Long id);
    CarResponse updateCar(Long id, CreateCarDTO createCarDTO);
    List<CarResponse> getAllCars();

}
