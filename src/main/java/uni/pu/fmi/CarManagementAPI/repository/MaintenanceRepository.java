package uni.pu.fmi.CarManagementAPI.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import uni.pu.fmi.CarManagementAPI.model.Maintenance;

@Repository
public interface MaintenanceRepository extends ListCrudRepository<Maintenance,Long> {
}
