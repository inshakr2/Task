package chany.task.MedicalRecord2.domain;

import chany.task.MedicalRecord2.dto.PatientDto;
import chany.task.MedicalRecord2.dto.VisitDto;
import chany.task.MedicalRecord2.repository.HospitalRepository;
import chany.task.MedicalRecord2.repository.PatientRepository;
import chany.task.MedicalRecord2.repository.VisitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class domianTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    VisitRepository visitRepository;

    @Autowired
    HospitalRepository hospitalRepository;


    @Test
    public void builder() {
        Patient patient = Patient.builder()
                .name("chany")
                .gender("M")
                .birth("930208")
                .build();
        Visit visit = Visit.builder()
                .patient(patient)
                .dateTime(LocalDateTime.now())
                .build();

        assertThat(patient).isNotNull();
        assertThat(visit).isNotNull();

    }

    @Test
    public void javaBean() {

        Patient patient = new Patient();
        String name = "Chany";
        String gender = "M";

        patient.setName(name);
        patient.setGender(gender);

        assertThat(patient.getName()).isEqualTo(name);
        assertThat(patient.getGender()).isEqualTo(gender);

    }

    @Test
    public void registPatient() {

        Hospital hospital = Hospital.builder()
                .name("세브란스")
                .chiefName("홍길동")
                .build();

        this.hospitalRepository.save(hospital);

        PatientDto patientDto = PatientDto.builder()
                .name("유창열")
                .birth("1993-02-08")
                .gender("M")
                .phoneNumber("010-1111-1111")
                .build();
        Patient registPatient = this.modelMapper.map(patientDto, Patient.class);

        Visit currentVisit = this.modelMapper.map(new VisitDto(), Visit.class);
        registPatient.setHospital(hospital);
        registPatient.register(currentVisit);


        this.patientRepository.save(registPatient);
        this.visitRepository.save(currentVisit);

        assertThat(registPatient.getVisits().get(0).getDateTime()).isEqualTo(currentVisit.getDateTime());
        assertThat(registPatient.getHospital().getName()).isEqualTo(hospital.getName());
        assertThat(currentVisit.getPatient().getId()).isEqualTo(registPatient.getId());
    }
}
