package uni.pu.fmi.CarManagementAPI.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uni.pu.fmi.CarManagementAPI.dto.response.GarageDailyAvailabilityReportDTO;
import uni.pu.fmi.CarManagementAPI.model.Garage;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface GarageRepository extends ListCrudRepository<Garage, Long> {
    List<Garage> findByCity(String city);

   @Query("""
			select new uni.pu.fmi.CarManagementAPI.dto.response.GarageDailyAvailabilityReportDTO 
			(m.scheduledDate, count(m.maintenanceId), (:totalCapacity - count(m.maintenanceId)))
			from Maintenance m 
			where 
				m.garage.garageId = :garageId and 
				m.scheduledDate >= :startDate and m.scheduledDate <= :endDate 
			group by m.scheduledDate
			"""
   )
    List<GarageDailyAvailabilityReportDTO> garageAvailability(@Param("garageId") Long garageId,
                                                              @Param("startDate") LocalDate startDate,
                                                              @Param("endDate") LocalDate endDate,
                                                              @Param("totalCapacity") Integer totalCapacity);
}
