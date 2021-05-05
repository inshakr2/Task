package chany.task.MedicalRecord2.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class domianTest {

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
}
