package chany.task.MedicalRecord3.controller;

import chany.task.MedicalRecord3.domain.Patient;
import chany.task.MedicalRecord3.dto.PatientDto;
import chany.task.MedicalRecord3.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/patient")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatient(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity createPatient(@RequestBody @Valid PatientDto patientDto) {
        Patient patient = patientService.savePatient(patientDto);
        URI location = linkTo(PatientController.class).slash(patient.getId()).toUri();
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

