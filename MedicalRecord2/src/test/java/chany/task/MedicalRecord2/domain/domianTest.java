package chany.task.MedicalRecord2.domain;

import chany.task.MedicalRecord2.dto.PatientDto;
import chany.task.MedicalRecord2.dto.RegisterDto;
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

        LocalDateTime currentTime = LocalDateTime.now();

        PatientDto patientDto = PatientDto.builder()
                .name("유창열")
                .birth("1993-02-08")
                .gender("M")
                .phoneNumber("010-1111-1111")
                .hospital(hospital)
                .build();
        Patient registPatient = this.modelMapper.map(patientDto, Patient.class);
        this.patientRepository.save(registPatient);

        VisitDto visitDto = VisitDto.builder()
                                    .dateTime(currentTime)
                                    .hospital(hospital)
                                    .patient(registPatient)
                                    .build();
        Visit currentVisit = this.modelMapper.map(visitDto, Visit.class);
        this.visitRepository.save(currentVisit);

        registPatient.getVisits().add(currentVisit);

        assertThat(registPatient.getVisits().get(0).getDateTime()).isEqualTo(currentVisit.getDateTime());
        assertThat(registPatient.getHospital().getName()).isEqualTo(hospital.getName());
        assertThat(currentVisit.getPatient().getId()).isEqualTo(registPatient.getId());
    }

    @Test
    public void register_entity2DTO() throws Exception {


        Register register = Register.builder()
                                .hospital(Hospital.builder()
                                            .chiefName("홍길동")
                                            .name("세브란스")
                                            .build())
                                .visit(Visit.builder()
                                            .dateTime(LocalDateTime.of(1993,2,8,12,00))
                                            .build())
                                .patient(Patient.builder()
                                            .name("chany")
                                            .birth("1993-02-08")
                                            .gender("M")
                                            .phoneNumber("017")
                                            .build())
                                .build();

        RegisterDto dto = this.modelMapper.map(register, RegisterDto.class);

        assertThat(dto.getHospitalName()).isEqualTo(register.getHospital().getName());
        assertThat(dto.getHospitalChiefName()).isEqualTo(register.getHospital().getChiefName());
        assertThat(dto.getVisitTime()).isEqualTo(register.getVisit().getDateTime());
        assertThat(dto.getPatientBirth()).isEqualTo(register.getPatient().getBirth());
        assertThat(dto.getPatientName()).isEqualTo(register.getPatient().getName());
        assertThat(dto.getPatientPhoneNumber()).isEqualTo(register.getPatient().getPhoneNumber());
        assertThat(dto.getPatientGender()).isEqualTo(register.getPatient().getGender());

    }

    @Test
    public void register_DTO2entity() throws Exception {

        RegisterDto dto = RegisterDto.builder()
                            .hospitalChiefName("홍길동")
                            .hospitalName("세브란스")
                            .patientBirth("1993-02-08")
                            .patientGender("M")
                            .patientName("chany")
                            .patientPhoneNumber("017")
                            .visitTime(LocalDateTime.of(1993,2,8,12,00))
                            .build();

        Register register = this.modelMapper.map(dto, Register.class);

        assertThat(register.getHospital().getName()).isEqualTo(dto.getHospitalName());
        assertThat(register.getHospital().getChiefName()).isEqualTo(dto.getHospitalChiefName());
        assertThat(register.getVisit().getDateTime()).isEqualTo(dto.getVisitTime());
        assertThat(register.getPatient().getName()).isEqualTo(dto.getPatientName());
        assertThat(register.getPatient().getBirth()).isEqualTo(dto.getPatientBirth());
        assertThat(register.getPatient().getGender()).isEqualTo(dto.getPatientGender());
        assertThat(register.getPatient().getPhoneNumber()).isEqualTo(dto.getPatientPhoneNumber());

    }
}
