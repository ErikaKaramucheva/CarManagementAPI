package uni.pu.fmi.CarManagementAPI.service;

import org.springframework.stereotype.Service;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.UpdateCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseGarageDTO;
import uni.pu.fmi.CarManagementAPI.model.Garage;

import java.util.List;

@Service
public interface CarService {
    ResponseCarDTO createCar(CreateCarDTO createCarDTO);

    ResponseCarDTO deleteCarById(Long id);

    ResponseCarDTO getCarById(Long id);

    ResponseCarDTO updateCar(Long id, UpdateCarDTO updateCarDTO);

    List<ResponseCarDTO> getAllCars(String carMake, Long garageId, String city, Integer fromYear, Integer toYear);

    Garage mapResponseGarageToGarage(ResponseGarageDTO garageResponse);

}
