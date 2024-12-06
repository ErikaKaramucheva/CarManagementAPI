package uni.pu.fmi.CarManagementAPI.model;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class Maintenance {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    @JoinColumn(name="id")
    private Car car;
    @Column(nullable = false)
    @Timestamp
    private Instant scheduledDate;
    @OneToMany
    @JoinColumn(name="id")
    private Garage garage;
}
