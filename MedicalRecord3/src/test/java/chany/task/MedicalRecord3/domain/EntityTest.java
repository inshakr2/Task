package chany.task.MedicalRecord3.domain;

import chany.task.MedicalRecord3.repository.HospitalRepository;
import chany.task.MedicalRecord3.repository.PatientRepository;
import chany.task.MedicalRecord3.repository.VisitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EntityTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    VisitRepository visitRepository;

    @Autowired
    PatientRepository patientRepository;


    @Test
    public void entityTest() {
        Hospital hospital = new Hospital("Sebrance", "SB", "Chany");
        hospitalRepository.save(hospital);

        Visit visit = new Visit(hospital, LocalDateTime.now(), "1");
        visitRepository.save(visit);

        PatientCodeSeq patientCodeSeq = new PatientCodeSeq();
        Patient patient = new Patient(hospital, "Yu", "M", "930208",
                "01029785737", patientCodeSeq);

        patientRepository.save(patient);

        Hospital findHP = hospitalRepository.findById(hospital.getId()).get();
        Patient findPT = patientRepository.findById(patient.getId()).get();
        Visit findVT = visitRepository.findById(visit.getId()).get();

        assertThat(findHP.getId()).isEqualTo(hospital.getId());
        assertThat(findPT.getId()).isEqualTo(patient.getId());
        assertThat(findVT.getId()).isEqualTo(visit.getId());

    }
}