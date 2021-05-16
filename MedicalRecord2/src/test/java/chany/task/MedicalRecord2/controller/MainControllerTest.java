package chany.task.MedicalRecord2.controller;

import chany.task.MedicalRecord2.dto.PatientDto;
import chany.task.MedicalRecord2.dto.RegisterDto;
import chany.task.MedicalRecord2.repository.HospitalRepository;
import chany.task.MedicalRecord2.repository.PatientRepository;
import chany.task.MedicalRecord2.repository.VisitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    }

    @Test
    public void 환자등록() throws Exception {
        // given
        RegisterDto registerInfo = RegisterDto.builder()
                .hospitalChiefName("홍길동")
                .hospitalName("세브란스")
                .patientBirth("1993-02-08")
                .patientGender("M")
                .patientName("chany")
                .patientPhoneNumber("017")
                .visitTime(LocalDateTime.of(1993,2,8,12,00))
                .build();

        this.mockMvc.perform(post("/api/main/patients")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.objectMapper.writeValueAsString(registerInfo)))
                        .andDo(print())
                        .andExpect(status().isCreated())
        ;
    }

    @Test
    public void 빈_입력값_환자등록_404() throws Exception {
        PatientDto patientDto = new PatientDto();

        this.mockMvc.perform(post("/api/main/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(patientDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}