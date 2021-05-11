package chany.task.MedicalRecord2.controller;

import chany.task.MedicalRecord2.domain.Patient;
import chany.task.MedicalRecord2.dto.PatientDto;
import chany.task.MedicalRecord2.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class PatientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PatientRepository patientRepository;

    @Before
    public void setUp() {
        this.patientRepository.deleteAll();
    }

    @Test
    public void patient생성() throws Exception{
        PatientDto patientDto = PatientDto.builder()
                                    .name("Chany")
                                    .gender("M")
                                    .birth("930208")
                                    .build();

        this.mockMvc.perform(post("/api/patients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(patientDto)))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("name").exists())
                    .andExpect(jsonPath("gender").exists())
                    .andExpect(jsonPath("birth").exists())
                    .andExpect(header().exists(HttpHeaders.LOCATION));
    }

    @Test
    public void 빈입력값_patient생성_400() throws Exception{
        PatientDto patientDto = new PatientDto();

        this.mockMvc.perform(post("/api/patients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(patientDto)))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
    }

    @Test
    public void patient조회() throws Exception {
        Patient patient = this.generatePatient(1);

        this.mockMvc.perform(get("/api/patients/{id}", patient.getId()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("name").value("유창열1"));
    }

    @Test
    public void 없는_patient조회_404() throws Exception {
        this.patientRepository.deleteAll();

        Patient patient = this.generatePatient(100);

        this.mockMvc.perform(get("/api/patients/0"))
                    .andExpect(status().isNotFound());
    }

    @Test
    public void patient수정() throws Exception {
        Patient patient = this.generatePatient(1);
        PatientDto updatePatient = this.modelMapper.map(patient, PatientDto.class);
        String name = "CHANY";
        String birth = "1993-02-08";
        updatePatient.setName(name);
        updatePatient.setBirth(birth);

        this.mockMvc.perform(put("/api/patients/{id}", patient.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(updatePatient)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("name").value(name))
                    .andExpect(jsonPath("birth").value(birth));
    }

    @Test
    public void 없는_patient수정_404() throws Exception {
        this.patientRepository.deleteAll();
        PatientDto patientDto = PatientDto.builder()
                                    .name("CHANY")
                                    .birth("1993-02-08")
                                    .build();

        this.mockMvc.perform(put("/api/patients/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(patientDto)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void patient_빈값으로_수정_400() throws Exception {
        Patient patient = this.generatePatient(1);
        PatientDto updatePatient = new PatientDto();

        this.mockMvc.perform(put("/api/patients/{id}", patient.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(updatePatient)))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
    }

    @Test
    public void patient삭제() throws Exception {
        Patient patient = this.generatePatient(1);

        this.mockMvc.perform(delete("/api/patients/{id}", patient.getId()))
                        .andExpect(status().isOk());
    }

    @Test
    public void 없는_patient삭제_404() throws Exception {
        this.patientRepository.deleteAll();

        this.mockMvc.perform(delete("/api/patients/0"))
                        .andExpect(status().isNotFound());
    }


    private Patient generatePatient(int idx) {
        Patient patient = this.buildPatient(idx);
        return this.patientRepository.save(patient);

    }

    private Patient buildPatient(int idx) {
        return Patient.builder()
                .name("유창열" + idx)
                .code("A01")
                .gender("M")
                .birth("930208")
                .phoneNumber("017")
                .build();
    }


}