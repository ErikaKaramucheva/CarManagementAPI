package uni.pu.fmi.CarManagementAPI.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseCarDTO;
import uni.pu.fmi.CarManagementAPI.model.Car;
import uni.pu.fmi.CarManagementAPI.repository.CarRepository;
import uni.pu.fmi.CarManagementAPI.service.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    private ResponseCarDTO mapCarToCarResponse(Car carRequest) {
        ResponseCarDTO response = new ResponseCarDTO();
        response.setMake(carRequest.getMake());
        response.setModel(carRequest.getModel());
        response.setProductionYear(carRequest.getProductionYear());
        response.setLicensePlate(carRequest.getLicensePlate());
        return response;

    }

    private Car mapCarRequestToCar(CreateCarDTO carRequest) {
        Car car = new Car();
        car.setMake(carRequest.getMake());
        car.setModel(carRequest.getModel());
        car.setProductionYear(carRequest.getProductionYear());
        car.setLicensePlate(carRequest.getLicensePlate());
        return car;

    }

    @Override
    public ResponseCarDTO createCar(CreateCarDTO createCarDTO) {
        Car createdCar = mapCarRequestToCar(createCarDTO);
        createdCar = carRepository.save(createdCar);
        return mapCarToCarResponse(createdCar);
    }

    @Override
    public ResponseCarDTO deleteCarById(Long id) {
        Optional<Car> currentCar = carRepository.findById(id);
        if (!currentCar.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }
        Car car = currentCar.get();
        carRepository.delete(car);
        return mapCarToCarResponse(car);
    }

    @Override
    public ResponseCarDTO getCarById(Long id) {
        Optional<Car> currentCar = carRepository.findById(id);
        if (!currentCar.isPresent()) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Car Not Found");
        }
        Car car = currentCar.get();
        return mapCarToCarResponse(car);
    }

    @Override
    public ResponseCarDTO updateCar(Long id, CreateCarDTO createCarDTO) {
        Optional<Car> currentCar = carRepository.findById(id);
        if (!currentCar.isPresent()) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Car Not Found");
        }
        Car car = currentCar.get();
        car.setCarId(id);
        car.setMake(createCarDTO.getMake());
        car.setModel(createCarDTO.getModel());
        car.setLicensePlate(createCarDTO.getLicensePlate());
        car.setProductionYear(createCarDTO.getProductionYear());
        car = carRepository.save(car);
        return mapCarToCarResponse(car);
    }

    @Override
    public List<ResponseCarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<ResponseCarDTO> responseList = new ArrayList<>();
        for (Car c : cars) {
            responseList.add(mapCarToCarResponse(c));
        }
        return responseList;
    }

}
