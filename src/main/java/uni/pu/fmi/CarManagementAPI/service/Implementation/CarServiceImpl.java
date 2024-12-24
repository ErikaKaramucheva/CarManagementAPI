package uni.pu.fmi.CarManagementAPI.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.CarResponse;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageResponse;
import uni.pu.fmi.CarManagementAPI.model.Car;
import uni.pu.fmi.CarManagementAPI.model.Garage;
import uni.pu.fmi.CarManagementAPI.repository.CarRepository;
import uni.pu.fmi.CarManagementAPI.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    private CarResponse mapCarToCarResponse(Car carRequest){
        CarResponse response = new CarResponse();
        response.setMake(carRequest.getMake());
        response.setModel(carRequest.getModel());
        //response.setProductionYear(carRequest.getProductionYear());
        response.setLicensePlate(carRequest.getLicensePlate());
        return response;

    }

    private Car mapCarRequestToCar(CreateCarDTO carRequest){
        Car car = new Car();
        car.setMake(carRequest.getMake());
        car.setModel(carRequest.getModel());

        //car.setProductionYear(carRequest.getProductionYear());
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
        return null;
    }

    @Override
    public CarResponse getCarById(Long id) {
        return null;
    }

    @Override
    public CarResponse updateCar(Long id, CreateCarDTO createCarDTO) {
        return null;
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
