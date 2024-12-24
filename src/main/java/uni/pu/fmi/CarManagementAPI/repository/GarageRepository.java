package uni.pu.fmi.CarManagementAPI.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import uni.pu.fmi.CarManagementAPI.model.Garage;

@Repository
public interface GarageRepository extends ListCrudRepository<Garage,Long> {
}
