package uni.pu.fmi.CarManagementAPI.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.UpdateCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseGarageDTO;
import uni.pu.fmi.CarManagementAPI.model.Car;
import uni.pu.fmi.CarManagementAPI.model.Garage;
import uni.pu.fmi.CarManagementAPI.repository.CarRepository;
import uni.pu.fmi.CarManagementAPI.service.CarService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private GarageServiceImpl garageService;

    private ResponseCarDTO mapCarToCarResponse(Car carRequest) {
        ResponseCarDTO response = new ResponseCarDTO();
        response.setId(carRequest.getCarId());
        response.setMake(carRequest.getMake());
        response.setModel(carRequest.getModel());
        response.setProductionYear(carRequest.getProductionYear());
        response.setLicensePlate(carRequest.getLicensePlate());
        List<ResponseGarageDTO> newGarages = new ArrayList<>();
        carRequest.getGarages().forEach(garageId -> newGarages.add(garageService.getGarageById(garageId.getGarageId())));
        response.setGarages(newGarages);
        return response;

    }

    private Car mapCarRequestToCar(CreateCarDTO carRequest) {
        Car car = new Car();
        car.setMake(carRequest.getMake());
        car.setModel(carRequest.getModel());
        car.setProductionYear(carRequest.getProductionYear());
        car.setLicensePlate(carRequest.getLicensePlate());
        List<Garage> newGarages = new ArrayList<>();
        List<Long> garageList= (List<Long>) carRequest.getGarageIds();
        for(int i=0;i<garageList.size();i++){
            ResponseGarageDTO g=garageService.getGarageById(garageList.get(i));
            Garage garage = new Garage();
            garage.setGarageId(g.getId());
            garage.setName(g.getName());
            garage.setCity(g.getCity().toLowerCase());
            garage.setLocation(g.getLocation());
            garage.setCapacity(g.getCapacity());
            newGarages.add(garage);
        }
        car.setGarages(newGarages);
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
    public ResponseCarDTO updateCar(Long id, UpdateCarDTO updateCarDTO) {
        Optional<Car> currentCar = carRepository.findById(id);
        if (!currentCar.isPresent()) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Car Not Found");
        }
        Car car = currentCar.get();
        car.setCarId(id);
        car.setMake(updateCarDTO.getMake());
        car.setModel(updateCarDTO.getModel());
        car.setLicensePlate(updateCarDTO.getLicensePlate());
        car.setProductionYear(updateCarDTO.getProductionYear());
        List<Garage> garages=new ArrayList<>();
        List<Long> garageList= (List<Long>) updateCarDTO.getGarageIds();
        for(int i=0;i<garageList.size();i++){
            ResponseGarageDTO g=garageService.getGarageById(garageList.get(i));
            Garage garage = new Garage();
            garage.setGarageId(g.getId());
            garage.setName(g.getName());
            garage.setCity(g.getCity().toLowerCase());
            garage.setLocation(g.getLocation());
            garage.setCapacity(g.getCapacity());
            garages.add(garage);
        }
        car.setGarages(garages);
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
    public List<ResponseCarDTO> getAllCars(String carMake, Long garageId, String city,
                                           Integer fromYear, Integer toYear) {
        List<Car> cars = carRepository.getAllCars(carMake,
                garageId,
                city,
                fromYear,
                toYear);
        List<ResponseCarDTO> responseList = new ArrayList<>();
        for (Car c : cars) {
            responseList.add(mapCarToCarResponse(c));
        }
        return responseList;
    }

}
