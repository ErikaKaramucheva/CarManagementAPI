package uni.pu.fmi.CarManagementAPI.dto.response;

import lombok.*;

import java.time.YearMonth;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyRequestsReportDTO {
    private YearMonth yearMonth;
    private int requests;

    public MonthlyRequestsReportDTO(int year, int month, long requests) {
        this.yearMonth = YearMonth.of(year, month);
        this.requests = (int)requests;
    }

}
