package chany.task.MedicalRecord3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MedicalRecord3Application {

	public static void main(String[] args) {
		SpringApplication.run(MedicalRecord3Application.class, args);
	}

}
