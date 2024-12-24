package uni.pu.fmi.CarManagementAPI.dto.response;

import lombok.*;

import java.time.Year;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private String make;
    private String model;
    private Integer productionYear;
    private String licensePlate;
}
