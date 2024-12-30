package uni.pu.fmi.CarManagementAPI.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateMaintenanceDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.UpdateMaintenanceDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.MonthlyRequestsReportDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseCarDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseMaintenanceDTO;
import uni.pu.fmi.CarManagementAPI.model.Car;
import uni.pu.fmi.CarManagementAPI.model.Garage;
import uni.pu.fmi.CarManagementAPI.model.Maintenance;
import uni.pu.fmi.CarManagementAPI.repository.MaintenanceRepository;
import uni.pu.fmi.CarManagementAPI.service.CarService;
import uni.pu.fmi.CarManagementAPI.service.GarageService;
import uni.pu.fmi.CarManagementAPI.service.MaintenanceService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Autowired
    private GarageService garageService;
    @Autowired
    private CarService carService;

    private ResponseMaintenanceDTO mapMaintenanceToMaintenanceResponse(Maintenance m){
        ResponseMaintenanceDTO response=new ResponseMaintenanceDTO();
        response.setMaintenanceId(m.getMaintenanceId());
        response.setCarId(m.getCar().getCarId());
        response.setCarName(m.getCar().getMake());
        response.setServiceType(m.getServiceType());
        response.setGarageId(m.getGarage().getGarageId());
        response.setScheduledDate(m.getScheduledDate());
        response.setGarageName(m.getGarage().getName());
        return response;
    }

    public Car mapResponseCarToCar(ResponseCarDTO responseCar){
        Car car= new Car();
        car.setMake(responseCar.getMake());
        car.setModel(responseCar.getModel());
        car.setLicensePlate(responseCar.getLicensePlate());
        car.setProductionYear(responseCar.getProductionYear());
        car.setCarId(responseCar.getId());
        return car;
    }


    private Maintenance mapMaintenanceRequestToMaintenance(CreateMaintenanceDTO m){
        Maintenance maintenance= new Maintenance();
        maintenance.setServiceType(m.getServiceType());
        maintenance.setScheduledDate(m.getScheduledDate());
        ResponseCarDTO responseCar=carService.getCarById(m.getCarId());
        ResponseGarageDTO garageResponse = garageService.getGarageById(m.getGarageId());
        maintenance.setCar(mapResponseCarToCar(responseCar));
        maintenance.setGarage(carService.mapResponseGarageToGarage(garageResponse));
        return maintenance;
    }

    @Override
    public ResponseMaintenanceDTO createMaintenance(CreateMaintenanceDTO maintenanceDTO) {
        Maintenance createdMaintenance = mapMaintenanceRequestToMaintenance(maintenanceDTO);
        createdMaintenance=maintenanceRepository.save(createdMaintenance);
        return mapMaintenanceToMaintenanceResponse(createdMaintenance);
    }

    @Override
    public ResponseMaintenanceDTO deleteMaintenanceById(Long id) {
        Optional<Maintenance> currentMaintenance = maintenanceRepository.findById(id);
        if (!currentMaintenance.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Maintenance not found");
        }
        Maintenance m = currentMaintenance.get();
        maintenanceRepository.delete(m);
        return mapMaintenanceToMaintenanceResponse(m);
    }

    @Override
    public ResponseMaintenanceDTO getMaintenanceById(Long id) {
        Optional<Maintenance> currentMaintenance = maintenanceRepository.findById(id);
        if (!currentMaintenance.isPresent()) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Maintenance Not Found");
        }
        Maintenance maintenance = currentMaintenance.get();
        return mapMaintenanceToMaintenanceResponse(maintenance);
    }

    @Override
    public ResponseMaintenanceDTO updateMaintenance(Long id, UpdateMaintenanceDTO updateMaintenanceDTO) {
        Optional<Maintenance> currentMaintenance = maintenanceRepository.findById(id);
        if (!currentMaintenance.isPresent()) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Maintenance Not Found");
        }
        Maintenance maintenance=currentMaintenance.get();
        maintenance.setMaintenanceId(id);
        maintenance.setServiceType(updateMaintenanceDTO.getServiceType());
        maintenance.setScheduledDate(updateMaintenanceDTO.getScheduledDate());
        ResponseCarDTO responseCar=carService.getCarById(updateMaintenanceDTO.getCarId());
        maintenance.setCar(mapResponseCarToCar(responseCar));
        ResponseGarageDTO garageResponse = garageService.getGarageById(updateMaintenanceDTO.getGarageId());
        maintenance.setGarage(carService.mapResponseGarageToGarage(garageResponse));
        maintenance=maintenanceRepository.save(maintenance);
        return mapMaintenanceToMaintenanceResponse(maintenance);
    }

    @Override
    public List<ResponseMaintenanceDTO> getAllMaintenances(Long carId, Long garageId, LocalDate startDate, LocalDate endDate) {
        List<Maintenance> maintenances = maintenanceRepository.getAllMaintenances(carId,garageId,startDate,endDate);
        List<ResponseMaintenanceDTO> responseList = new ArrayList<>();
        for (Maintenance m : maintenances) {
            responseList.add(mapMaintenanceToMaintenanceResponse(m));
        }
        return responseList;
    }

    @Override
    public List<MonthlyRequestsReportDTO> getMonthlyRequestsReport(Long garageId, YearMonth startMonth, YearMonth endMonth) {
        LocalDate startDate= startMonth.atDay(1);
        LocalDate endDate= endMonth.atEndOfMonth();
        List<MonthlyRequestsReportDTO> monthlyRequests = maintenanceRepository.getMonthlyRequestsReport(garageId,startDate,endDate);
        for(YearMonth currentMonth = startMonth, end = endMonth; !currentMonth.isAfter(end); currentMonth = currentMonth.plusMonths(1)) {
            final YearMonth currentYearMonth = currentMonth;
            if(!monthlyRequests
                    .stream()
                    .anyMatch(req -> req.getYearMonth().equals(currentYearMonth))) {
                monthlyRequests.add(new MonthlyRequestsReportDTO(currentMonth, 0));
            }
        }
        //monthlyRequests.stream().sorted();
        monthlyRequests = monthlyRequests.stream()
                .sorted(Comparator.comparing(MonthlyRequestsReportDTO::getYearMonth))
                .collect(Collectors.toList());
        return monthlyRequests;
    }
}
