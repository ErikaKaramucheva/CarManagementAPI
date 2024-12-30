package uni.pu.fmi.CarManagementAPI.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class CreateMaintenanceDTO {
    @NotNull(message = "Garage id is required!")
    private Long garageId;
    @NotNull(message = "Car id is required!")
    private Long carId;
    @NotBlank(message = "Service type is required!")
    @Size(min=1)
    private String serviceType;
    @NotNull(message = "Scheduled date is required!")
    private LocalDate scheduledDate;
}
