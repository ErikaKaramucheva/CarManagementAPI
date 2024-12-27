package uni.pu.fmi.CarManagementAPI.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import uni.pu.fmi.CarManagementAPI.model.Garage;

import java.util.Set;

@Repository
public interface GarageRepository extends ListCrudRepository<Garage, Long> {
    Set<Garage> findGaragesByCity(String city);
}
