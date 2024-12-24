package uni.pu.fmi.CarManagementAPI.model;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long maintenanceId;
    @Column(nullable = false)
    private String serviceType;
    @ManyToOne
    @JoinColumn(name="carId",nullable = false)
    private Car car;
    @Column(nullable = false)
    private LocalDate scheduledDate;
    @ManyToOne
    @JoinColumn(name="garageId",nullable = false)
    private Garage garage;
}
