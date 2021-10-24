package chany.task.MedicalRecord5.controller;

import chany.task.MedicalRecord5.domain.Patient;
import chany.task.MedicalRecord5.dto.PatientDto;
import chany.task.MedicalRecord5.dto.PatientQueryDto;
import chany.task.MedicalRecord5.dto.PatientResponseDto;
import chany.task.MedicalRecord5.dto.PatientSearchCondition;
import chany.task.MedicalRecord5.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatient(id);
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/all")
    public ResponseEntity getPatients() {
        List<PatientResponseDto> patients = patientService.getPatients();
        return ResponseEntity.ok(patients.isEmpty() ? "검색결과가 없습니다." : patients);
    }

    @GetMapping("/search")
    public ResponseEntity getPatientsByCondition(@RequestBody PatientSearchCondition condition,
                                                 @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        List<PatientQueryDto> results = patientService.getPatientsByCondition(condition, pageNo -1, pageSize);
        return ResponseEntity.ok(results.isEmpty() ? "검색결과가 없습니다." : results);
    }

    @PostMapping
    public ResponseEntity createPatient(@RequestBody @Valid PatientDto patientDto) {
        Patient patient = patientService.createPatient(patientDto);
        URI location = linkTo(VisitController.class).slash(patient.getId()).toUri();
        return ResponseEntity.created(location).body(patient);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updatePatient(@PathVariable Long id,
                                        @RequestBody @Valid PatientDto patientDto) {
        Patient patient = patientService.updatePatient(id, patientDto);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }
}