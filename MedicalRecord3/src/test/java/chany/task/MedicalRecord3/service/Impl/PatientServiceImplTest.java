package chany.task.MedicalRecord3.service.Impl;

import chany.task.MedicalRecord3.domain.Patient;
import chany.task.MedicalRecord3.dto.PatientDto;
import chany.task.MedicalRecord3.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PatientServiceImplTest {

    @Autowired
    PatientService patientService;

    @Test
    public void patient등록() throws Exception {
        // given
        PatientDto patientDto = PatientDto.builder()
                .hospitalId(1L)
                .name("CHANY")
                .genderCode("M")
                .build();

        // when
        Patient patient = patientService.savePatient(patientDto);

        // then
        assertThat(patient.getPatientName()).isEqualTo("CHANY");
    }

    @Test
    public void patient조회() throws Exception {
        // given
        PatientDto patientDto = PatientDto.builder()
                .hospitalId(1L)
                .name("CHANY")
                .genderCode("M")
                .build();

        Patient patient = patientService.savePatient(patientDto);
        // when
        Patient findPatient = patientService.getPatient(patient.getId());

        // then
        assertThat(findPatient.getPatientName()).isEqualTo("CHANY");

    }

    @Test
    public void patient수정() throws Exception {
        // given
        PatientDto patientDto = PatientDto.builder()
                .hospitalId(1L)
                .name("CHANY")
                .genderCode("M")
                .build();

        Patient patient = patientService.savePatient(patientDto);

        // when
        PatientDto updateDto = PatientDto.builder()
                .hospitalId(5L)
                .name("yeol")
                .genderCode("M")
                .build();
        Patient updatePatient = patientService.updatePatient(patient.getId(), updateDto);

        // then
        assertThat(updatePatient.getPatientName()).isEqualTo("yeol");

    }
}