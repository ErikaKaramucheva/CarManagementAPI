package uni.pu.fmi.CarManagementAPI.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageDailyAvailabilityReportDTO;
import uni.pu.fmi.CarManagementAPI.model.Garage;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface GarageRepository extends ListCrudRepository<Garage, Long> {
    List<Garage> findByCity(String city);

   /*@Query("SELECT g FROM Garage g " +
                    "JOIN g.maintenances m " +
                    "WHERE g.garageId = : garageId and " +
            "m.scheduledDate <= :endDate AND m.scheduledDate >= :startDate")*/
   /* @Query("SELECT m.scheduledDate, COUNT(m), capacity - COUNT(m) " +
            "FROM Maintenance m " +
            "WHERE m.garage.id = :garageId AND m.scheduledDate BETWEEN :startDate AND :endDate " +
            "GROUP BY m.scheduledDate")*/
    //List<GarageDailyAvailabilityReportDTO> garageAvailability(Long garageId, LocalDate startDate, LocalDate endDate);
}
