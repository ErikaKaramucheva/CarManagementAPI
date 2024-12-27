package uni.pu.fmi.CarManagementAPI.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.YearMonth;

@Data
@Setter
@Getter
public class MonthlyRequestsReportDTO {
    private YearMonth yearMonth;
    private int requests;
}
