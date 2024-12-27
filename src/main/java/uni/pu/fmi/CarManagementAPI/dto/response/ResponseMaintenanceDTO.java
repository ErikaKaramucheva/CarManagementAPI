package uni.pu.fmi.CarManagementAPI.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class ResponseMaintenanceDTO {
    private Long maintenanceId;
    private Long carId;
    private String carName;
    private String serviceType;
    private LocalDate scheduledDate;
    private Long garageId;
    private String garageName;
}
