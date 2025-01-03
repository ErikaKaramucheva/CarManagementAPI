package uni.pu.fmi.CarManagementAPI.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.request.UpdateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageDailyAvailabilityReportDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseGarageDTO;
import uni.pu.fmi.CarManagementAPI.model.Garage;
import uni.pu.fmi.CarManagementAPI.repository.GarageRepository;
import uni.pu.fmi.CarManagementAPI.service.GarageService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GarageServiceImpl implements GarageService {

    @Autowired
    private GarageRepository garageRepository;

    private ResponseGarageDTO mapGarageToGarageResponse(Garage garageRequest) {
        ResponseGarageDTO response = new ResponseGarageDTO();
        response.setId(garageRequest.getGarageId());
        response.setName(garageRequest.getName());
        response.setLocation(garageRequest.getLocation());
        response.setCity(garageRequest.getCity().toLowerCase());
        response.setCapacity(garageRequest.getCapacity());
        return response;

    }

    private Garage mapGarageRequestToGarage(CreateGarageDTO garageRequest) {
        Garage garage = new Garage();
        garage.setName(garageRequest.getName());
        garage.setCity(garageRequest.getCity().toLowerCase());
        garage.setLocation(garageRequest.getLocation());
        garage.setCapacity(garageRequest.getCapacity());
        return garage;

    }

    @Override
    public ResponseGarageDTO addGarage(CreateGarageDTO createGarageDTO) {
        Garage newGarage = mapGarageRequestToGarage(createGarageDTO);
        newGarage.setCity(newGarage.getCity().toLowerCase());
        newGarage = garageRepository.save(newGarage);
        return mapGarageToGarageResponse(newGarage);
    }

    @Override
    public ResponseGarageDTO deleteGarageById(Long id) {
        Optional<Garage> currentGarage = garageRepository.findById(id);
        if (!currentGarage.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage not found");
        }
        Garage garage = currentGarage.get();
        garageRepository.delete(garage);
        return mapGarageToGarageResponse(garage);
    }

    @Override
    public ResponseGarageDTO getGarageById(Long id) {
        Optional<Garage> currentGarage = garageRepository.findById(id);
        if (!currentGarage.isPresent()) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Garage Not Found");
        }
        Garage garage = currentGarage.get();
        return mapGarageToGarageResponse(garage);
    }

    @Override
    public ResponseGarageDTO updateGarage(Long id, UpdateGarageDTO updateGarageDTO) {
        Optional<Garage> currentGarage = garageRepository.findById(id);
        if (!currentGarage.isPresent()) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Garage Not Found");
        }
        Garage garage = currentGarage.get();
        garage.setName(updateGarageDTO.getName());
        garage.setCity(updateGarageDTO.getCity().toLowerCase());
        garage.setLocation(updateGarageDTO.getLocation());
        garage.setCapacity(updateGarageDTO.getCapacity());
        garage = garageRepository.save(garage);
        return mapGarageToGarageResponse(garage);
    }

    @Override
    public List<GarageDailyAvailabilityReportDTO> dailyAvailabilityReport(Long garageId, LocalDate startDate, LocalDate endDate) {
        Optional<Garage> currentGarage = garageRepository.findById(garageId);
        if (!currentGarage.isPresent()) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Garage Not Found");
        }
        if (endDate.isBefore(startDate)) {
            throw new ResponseStatusException((HttpStatus.BAD_REQUEST), "Invalid Input Data - End Date Is Before Start Date");
        }
        List<GarageDailyAvailabilityReportDTO> report = garageRepository.garageAvailability(garageId,startDate,endDate,currentGarage.get().getCapacity());
        for(LocalDate currentDate = startDate, end = endDate; !currentDate.isAfter(end); currentDate = currentDate.plusDays(1)) {
            final LocalDate currentLocalDate = currentDate;
            if(!report
                    .stream()
                    .anyMatch(req -> req.getDate().equals(currentLocalDate))) {
                report.add(new GarageDailyAvailabilityReportDTO(currentDate, 0,currentGarage.get().getCapacity()));
            }
        }
        report = report.stream()
                .sorted(Comparator.comparing(GarageDailyAvailabilityReportDTO::getDate))
                .collect(Collectors.toList());

        return report;
    }

    @Override
    public List<ResponseGarageDTO> getGaragesByCity(String city) {
        List<Garage> garages;
        if (city == null) {
            garages = (List<Garage>) garageRepository.findAll();
        } else {
            garages = (List<Garage>) garageRepository.findByCity(city.toLowerCase());
        }
        List<ResponseGarageDTO> responseList = new ArrayList<>();
        for (Garage g : garages) {
            responseList.add(mapGarageToGarageResponse(g));
        }
        return responseList;
    }
}
