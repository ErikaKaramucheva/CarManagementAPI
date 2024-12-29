package uni.pu.fmi.CarManagementAPI.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uni.pu.fmi.CarManagementAPI.dto.response.MonthlyRequestsReportDTO;
import uni.pu.fmi.CarManagementAPI.model.Maintenance;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Repository
public interface MaintenanceRepository extends ListCrudRepository<Maintenance, Long> {
    @Query("Select m FROM Maintenance m " +
            "WHERE (:carId IS NULL OR m.car.carId = :carId) " +
            "AND (:garageId IS NULL OR m.garage.garageId = :garageId) " +
            "AND (:startDate IS NULL OR m.scheduledDate >= :startDate) " +
            "AND (:endDate IS NULL OR m.scheduledDate <= :endDate)")
    List<Maintenance> getAllMaintenances(@Param("carId") Long carId,
                         @Param("garageId") Long garageId,
                         @Param("startDate") LocalDate startDate,
                         @Param("endDate") LocalDate endDate);

    @Query("""
			select new uni.pu.fmi.CarManagementAPI.dto.response.MonthlyRequestsReportDTO
			(year(m.scheduledDate), month(m.scheduledDate), count(m.maintenanceId)) from Maintenance m 
			where 
				m.garage.garageId = :garageId and 
				m.scheduledDate >= :startDate and m.scheduledDate <= :endDate 
			group by year(m.scheduledDate), month(m.scheduledDate)
			"""
    )
    List<MonthlyRequestsReportDTO> getMonthlyRequestsReport(
            @Param("garageId") Long garageId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}
