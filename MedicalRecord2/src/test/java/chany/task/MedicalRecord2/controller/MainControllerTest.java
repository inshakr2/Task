package chany.task.MedicalRecord2.controller;

import chany.task.MedicalRecord2.domain.Hospital;
import chany.task.MedicalRecord2.domain.Patient;
import chany.task.MedicalRecord2.domain.Visit;
import chany.task.MedicalRecord2.dto.PatientDto;
import chany.task.MedicalRecord2.dto.VisitDto;
import chany.task.MedicalRecord2.repository.HospitalRepository;
import chany.task.MedicalRecord2.repository.PatientRepository;
import chany.task.MedicalRecord2.repository.VisitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class MainControllerTest {

    @Autowired
    MockMvc mockMvc;

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

    @Before
    public void setUp() {
        this.patientRepository.deleteAll();
        this.visitRepository.deleteAll();
        this.hospitalRepository.deleteAll();

        Hospital hospital = Hospital.builder()
                .name("세브란스")
                .chiefName("홍길동")
                .build();

        this.hospitalRepository.save(hospital);
    }

    @Test
    public void 환자등록() throws Exception {


        // Given
        PatientDto patientDto = PatientDto.builder()
                .name("유창열")
                .birth("1993-02-08")
                .gender("M")
                .phoneNumber("010-1111-1111")
                .build();


        this.mockMvc.perform(post("/api/main/patients")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.objectMapper.writeValueAsString(patientDto)))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("name").exists())
                        ;
    }
}