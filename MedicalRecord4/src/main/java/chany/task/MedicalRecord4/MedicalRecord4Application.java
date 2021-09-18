package chany.task.MedicalRecord4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MedicalRecord4Application {

	public static void main(String[] args) {
		SpringApplication.run(MedicalRecord4Application.class, args);
	}

}
