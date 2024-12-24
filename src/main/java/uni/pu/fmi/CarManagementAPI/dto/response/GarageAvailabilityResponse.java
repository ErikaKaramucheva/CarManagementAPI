package uni.pu.fmi.CarManagementAPI.dto.response;

import java.time.LocalDate;

public class GarageAvailabilityResponse {
    private LocalDate date;
    private int requests;
    private int availableCapacity;
}
