package chany.task.MedicalRecord2.controller;

import chany.task.MedicalRecord2.domain.Register;
import chany.task.MedicalRecord2.dto.PatientDto;
import chany.task.MedicalRecord2.dto.RegisterDto;
import chany.task.MedicalRecord2.dto.UpdateRegisterDto;
import chany.task.MedicalRecord2.repository.HospitalRepository;
import chany.task.MedicalRecord2.repository.PatientRepository;
import chany.task.MedicalRecord2.repository.VisitRepository;
import chany.task.MedicalRecord2.service.impl.RegisterServiceImpl;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    RegisterServiceImpl registerService;

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
                .visitDateTime(LocalDateTime.of(1993,2,8,12,00))
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

    @Test
    public void 환자수정() throws Exception {
        Register register = this.generateRegister();
        UpdateRegisterDto dto = this.modelMapper.map(register, UpdateRegisterDto.class);

        String name = "유창열";
        String birth = "1993-02-08";
        String gender = "M";
        String hospitalName = "Sebrance";

        dto.setPatientName(name);
        dto.setPatientBirth(birth);
        dto.setPatientGender(gender);
        dto.setHospitalName(hospitalName);

        this.mockMvc.perform(put("/api/main/patients/{id}", register.getPatient().getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.objectMapper.writeValueAsString(dto)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("name").value(name))
                    .andExpect(jsonPath("birth").value(birth))
                    .andExpect(jsonPath("gender").value(gender));

    }

    @Test
    public void 환자수정_없는환자_404() throws Exception {
        Register register = this.generateRegister();
        UpdateRegisterDto dto = this.modelMapper.map(register, UpdateRegisterDto.class);

        String name = "유창열";
        String birth = "1993-02-08";

        dto.setPatientName(name);
        dto.setPatientBirth(birth);

        this.mockMvc.perform(put("/api/main/patients/{id}", 0)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.objectMapper.writeValueAsString(dto)))
                        .andDo(print())
                        .andExpect(status().isNotFound());
    }

    @Test
    public void 환자수정_visit수정_400() throws Exception {
        Register register = this.generateRegister();
        RegisterDto registerDto = this.modelMapper.map(register, RegisterDto.class);

        String name = "유창열";
        LocalDateTime visit = LocalDateTime.of(1993,2,8,12,00);

        registerDto.setPatientName(name);
        registerDto.setVisitDateTime(visit);

        this.mockMvc.perform(put("/api/main/patients/{id}", register.getPatient().getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.objectMapper.writeValueAsString(registerDto)))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    public void 환자삭제() throws Exception {
        Register register = this.generateRegister();
        Long id = register.getPatient().getId();

        this.mockMvc.perform(delete("/api/main/patients/{id}", id))
                        .andExpect(status().isOk());
    }

    @Test
    public void 없는_환자삭제_404() throws Exception {
        this.patientRepository.deleteAll();

        this.mockMvc.perform(delete("/api/main/patients/0"))
                        .andExpect(status().isNotFound());
    }

    @Test
    public void 환자조회() throws Exception {

        Register register = this.generateRegister();
        Long id = register.getPatient().getId();

        this.mockMvc.perform(get("/api/main/patients/{id}", id))
                        .andDo(print())
                        .andExpect(status().isOk());


    }

    private Register generateRegister() {
        RegisterDto registerDto = RegisterDto.builder()
                                                .patientName("창열")
                                                .patientPhoneNumber("017")
                                                .patientGender("남자")
                                                .patientBirth("930208")
                                                .hospitalName("길병원")
                                                .hospitalChiefName("홍길동")
                                                .visitDateTime(LocalDateTime.now())
                                                .build();

        Register register = this.modelMapper.map(registerDto, Register.class);
        this.registerService.register(register);

        return register;
    }
}