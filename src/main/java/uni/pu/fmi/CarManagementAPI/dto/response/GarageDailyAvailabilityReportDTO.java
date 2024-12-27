package uni.pu.fmi.CarManagementAPI.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class GarageDailyAvailabilityReportDTO {
    private LocalDate date;
    private int requests;
    private int availableCapacity;
}
