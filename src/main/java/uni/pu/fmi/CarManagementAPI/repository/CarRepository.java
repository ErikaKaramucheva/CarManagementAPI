package uni.pu.fmi.CarManagementAPI.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uni.pu.fmi.CarManagementAPI.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends ListCrudRepository<Car, Long> {
    @Query("Select c FROM Car c " +
            "LEFT Join c.garages g " +
            "WHERE (:make IS NULL OR :make='' OR LOWER(c.make) LIKE LOWER(CONCAT('%', :make, '%'))) " +
            "AND (:garageId IS NULL OR g.garageId = :garageId) " +
            "AND (:city IS NULL OR :city='' OR LOWER(g.city) LIKE LOWER(CONCAT('%', :city, '%'))) " +
            "AND (:fromYear IS NULL OR c.productionYear >= :fromYear) " +
            "AND (:toYear IS NULL OR c.productionYear <= :toYear)")
    List<Car> getAllCars(@Param("make") String carMake,
                                    @Param("garageId") Long garageId,
                                    @Param("city") String city,
                                    @Param("fromYear") Integer fromYear,
                                    @Param("toYear") Integer toYear);
}
