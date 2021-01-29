package chany.task;

import chany.task.repository.PatientRepository;
import chany.task.repository.VisitRepository;
import chany.task.service.PatientService;
import chany.task.service.impl.PatientServiceImpl;
import chany.task.service.VisitService;
import chany.task.service.impl.VisitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private PatientRepository patientRepository;
    private VisitRepository visitRepository;

    @Autowired
    public SpringConfig(PatientRepository patientRepository, VisitRepository visitRepository) {
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
    }

    @Bean
    public PatientService patientService() {
        return new PatientServiceImpl(patientRepository);
    }

    @Bean
    public VisitService visitService() {
        return new VisitServiceImpl(visitRepository);
    }
}
