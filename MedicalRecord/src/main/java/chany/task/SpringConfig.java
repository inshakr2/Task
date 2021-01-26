package chany.task;

import chany.task.repository.PatientRepository;
import chany.task.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SpringConfig {
    private PatientRepository patientRepository;
    private VisitRepository visitRepository;

    @Autowired
    public SpringConfig(PatientRepository patientRepository, VisitRepository visitRepository) {
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
    }
}
