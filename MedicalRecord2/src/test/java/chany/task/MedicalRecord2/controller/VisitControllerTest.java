package chany.task.MedicalRecord2.controller;


import chany.task.MedicalRecord2.domain.Visit;
import chany.task.MedicalRecord2.dto.VisitDto;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VisitControllerTest {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    VisitRepository visitRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ModelMapper modelMapper;

    @Before
    public void setUp() {
        this.visitRepository.deleteAll();
        this.patientRepository.deleteAll();
    }

    @Test
    public void Visit생성() throws Exception {

        VisitDto visit = VisitDto.builder()
                .dateTime(LocalDateTime.now())
                .build();

        mockMvc.perform(post("/api/visits/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(visit)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
        ;
    }

    @Test
    public void 비어있는_입력값_Visit생성_400() throws Exception {

        VisitDto visit = VisitDto.builder().build();

        mockMvc.perform(post("/api/visits/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(visit)))
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void Visit조회() throws Exception {
        Visit visit = this.generateVisit();

        this.mockMvc.perform(get("/api/visits/{id}", visit.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("dateTime").exists())
                ;
    }

    @Test
    public void 없는Visit조회_404() throws Exception {
        this.visitRepository.deleteAll();

        this.mockMvc.perform(get("/api/visits/0"))
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    public void Visit수정() throws Exception {
        Visit visit = this.generateVisit();

        LocalDateTime newDate = LocalDateTime.of(1993, 2, 8, 20, 0);
        VisitDto updateVisit = this.modelMapper.map(visit, VisitDto.class);
        updateVisit.setDateTime(newDate);

        this.mockMvc.perform(put("/api/visits/{id}", visit.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(updateVisit))
                    )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("dateTime").exists());

    }

    @Test
    public void 없는_visit수정_404() throws Exception {
        this.visitRepository.deleteAll();
        Visit visit = this.generateVisit();
        VisitDto visitDto = this.modelMapper.map(visit, VisitDto.class);

        this.mockMvc.perform(put("/api/visits/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(visitDto)))
                    .andDo(print())
                    .andExpect(status().isNotFound());
    }

    @Test
    public void visit_빈값으로_수정_400() throws Exception {
        Visit visit = this.generateVisit();

        VisitDto updateVisit = new VisitDto();

        this.mockMvc.perform(put("/api/visits/{id}", visit.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(updateVisit)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void visit삭제() throws Exception{
        Visit visit = this.generateVisit();

        this.mockMvc.perform(delete("/api/visits/{id}", visit.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 없는visit삭제_404() throws Exception{
        this.visitRepository.deleteAll();

        this.mockMvc.perform(delete("/api/visits/0"))
                .andExpect(status().isNotFound());
    }


    private Visit generateVisit() {
        Visit visit = buildVisit();
        return this.visitRepository.save(visit);
    }

    private Visit buildVisit() {

        return Visit.builder()
                .dateTime(LocalDateTime.now())
                .build();
    }

}