package uni.pu.fmi.CarManagementAPI.service;

import org.springframework.stereotype.Service;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseCarDTO;

import java.util.List;

@Service
public interface CarService {
    ResponseCarDTO createCar(CreateCarDTO createCarDTO);

    ResponseCarDTO deleteCarById(Long id);

    ResponseCarDTO getCarById(Long id);

    ResponseCarDTO updateCar(Long id, CreateCarDTO createCarDTO);

    List<ResponseCarDTO> getAllCars();


}
