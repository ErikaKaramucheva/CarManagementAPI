package uni.pu.fmi.CarManagementAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CarManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarManagementApiApplication.class, args);
	}

}
