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
public class UpdateMaintenanceDTO {
    @NotNull(message = "Car id is required!")
    private Long carId;
    @NotBlank(message = "Service type is required!")
    @Size(min=1, message = "Service type must be at least 1 character.")
    private String serviceType;
    @NotNull(message = "Scheduled Date is required!")
    private LocalDate scheduledDate;
    @NotNull(message = "Garage id is required!")
    private Long garageId;
}
