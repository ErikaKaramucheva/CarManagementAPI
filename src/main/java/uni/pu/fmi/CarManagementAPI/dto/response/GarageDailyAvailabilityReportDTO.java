package uni.pu.fmi.CarManagementAPI.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
public class GarageDailyAvailabilityReportDTO {
    private LocalDate date;
    private int requests;
    private int availableCapacity;

    public GarageDailyAvailabilityReportDTO(LocalDate date, long requests, long availableCapacity) {
        this.date = date;
        this.requests = (int)requests;
        this.availableCapacity = (int)availableCapacity;
    }
}
