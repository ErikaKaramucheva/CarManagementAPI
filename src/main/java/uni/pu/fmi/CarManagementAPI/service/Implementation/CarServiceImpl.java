package uni.pu.fmi.CarManagementAPI.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.CarResponse;
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

    private CarResponse mapCarToCarResponse(Car carRequest){
        CarResponse response = new CarResponse();
        response.setMake(carRequest.getMake());
        response.setModel(carRequest.getModel());
        response.setProductionYear(carRequest.getProductionYear());
        response.setLicensePlate(carRequest.getLicensePlate());
        return response;

    }

    private Car mapCarRequestToCar(CreateCarDTO carRequest){
        Car car = new Car();
        car.setMake(carRequest.getMake());
        car.setModel(carRequest.getModel());
        car.setProductionYear(carRequest.getProductionYear());
        car.setLicensePlate(carRequest.getLicensePlate());
        return car;

    }

    @Override
    public CarResponse createCar(CreateCarDTO createCarDTO) {
        Car createdCar=mapCarRequestToCar(createCarDTO);
        createdCar=carRepository.save(createdCar);
        return mapCarToCarResponse(createdCar);
    }

    @Override
    public CarResponse deleteCarById(Long id) {
        Optional<Car> currentCar = carRepository.findById(id);
        if(!currentCar.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Car not found");
        }
        Car car=currentCar.get();
        carRepository.delete(car);
        return mapCarToCarResponse(car);
    }

    @Override
    public CarResponse getCarById(Long id) {
        Optional<Car> currentCar = carRepository.findById(id);
        if(!currentCar.isPresent()){
            throw new ResponseStatusException((HttpStatus.NOT_FOUND),"Car Not Found");
        }
        Car car = currentCar.get();
        return mapCarToCarResponse(car);
    }

    @Override
    public CarResponse updateCar(Long id, CreateCarDTO createCarDTO) {
        Optional<Car> currentCar = carRepository.findById(id);
        if(!currentCar.isPresent()){
            throw new ResponseStatusException((HttpStatus.NOT_FOUND),"Car Not Found");
        }
        Car car=currentCar.get();
        car.setCarId(id);
        car.setMake(createCarDTO.getMake());
        car.setModel(createCarDTO.getModel());
        car.setLicensePlate(createCarDTO.getLicensePlate());
        car.setProductionYear(createCarDTO.getProductionYear());
        car=carRepository.save(car);
        return mapCarToCarResponse(car);
    }

    @Override
    public List<CarResponse> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarResponse> responseList=new ArrayList<>();
        for(Car c : cars){
            responseList.add(mapCarToCarResponse(c));
        }
        return responseList;
    }

}
