package uni.pu.fmi.CarManagementAPI.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    @JoinColumn(name="carId")
    private Car car;
    @Column(nullable = false)
    private LocalDate scheduledDate;
    @ManyToOne
    @JoinColumn(name="garageId")
    private Garage garage;
}
