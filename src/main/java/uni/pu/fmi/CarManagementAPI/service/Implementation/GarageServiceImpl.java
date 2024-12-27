package uni.pu.fmi.CarManagementAPI.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uni.pu.fmi.CarManagementAPI.dto.request.CreateGarageDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.MonthlyRequestsReportDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageDailyAvailabilityReportDTO;
import uni.pu.fmi.CarManagementAPI.dto.response.ResponseGarageDTO;
import uni.pu.fmi.CarManagementAPI.model.Garage;
import uni.pu.fmi.CarManagementAPI.repository.GarageRepository;
import uni.pu.fmi.CarManagementAPI.service.GarageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GarageServiceImpl implements GarageService {

    @Autowired
    private GarageRepository garageRepository;

    private ResponseGarageDTO mapGarageToGarageResponse(Garage garageRequest) {
        ResponseGarageDTO response = new ResponseGarageDTO();
        response.setId(garageRequest.getGarageId());
        response.setName(garageRequest.getName());
        response.setLocation(garageRequest.getLocation());
        response.setCity(garageRequest.getCity());
        response.setCapacity(garageRequest.getCapacity());
        return response;

    }

    private Garage mapGarageRequestToGarage(CreateGarageDTO garageRequest) {
        Garage garage = new Garage();
        garage.setName(garageRequest.getName());
        garage.setCity(garageRequest.getCity());
        garage.setLocation(garageRequest.getLocation());
        garage.setCapacity(garageRequest.getCapacity());
        return garage;

    }

    @Override
    public ResponseGarageDTO addGarage(CreateGarageDTO createGarageDTO) {
        Garage newGarage = mapGarageRequestToGarage(createGarageDTO);
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
    public ResponseGarageDTO updateGarage(Long id, CreateGarageDTO updateGarageDTO) {
        Optional<Garage> currentGarage = garageRepository.findById(id);
        if (!currentGarage.isPresent()) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Garage Not Found");
        }
        Garage garage = currentGarage.get();
        garage.setName(updateGarageDTO.getName());
        garage.setCity(updateGarageDTO.getCity());
        garage.setLocation(updateGarageDTO.getLocation());
        garage.setCapacity(updateGarageDTO.getCapacity());
        garage = garageRepository.save(garage);
        return mapGarageToGarageResponse(garage);
    }

    @Override
    public List<ResponseGarageDTO> getAllGarages() {
        List<Garage> garages = garageRepository.findAll();
        List<ResponseGarageDTO> responseList = new ArrayList<>();
        for (Garage g : garages) {
            responseList.add(mapGarageToGarageResponse(g));
        }
        return responseList;
    }

    @Override
    public List<GarageDailyAvailabilityReportDTO> dailyAvailabilityReport(MonthlyRequestsReportDTO monthlyRequestsReportDTO) {
        return null;
    }

    @Override
    public List<ResponseGarageDTO> getGaragesByCity(String city) {
        List<Garage> garages = (List<Garage>) garageRepository.findGaragesByCity(city);
        List<ResponseGarageDTO> responseList = new ArrayList<>();
        for (Garage g : garages) {
            responseList.add(mapGarageToGarageResponse(g));
        }
        return responseList;
    }
}
