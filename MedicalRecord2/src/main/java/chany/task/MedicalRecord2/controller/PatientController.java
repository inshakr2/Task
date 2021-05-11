package chany.task.MedicalRecord2.controller;

import chany.task.MedicalRecord2.domain.Patient;
import chany.task.MedicalRecord2.dto.PatientDto;
import chany.task.MedicalRecord2.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/patients", produces = MediaTypes.HAL_JSON_VALUE)
public class PatientController {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public PatientController(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity createPatient(@RequestBody @Valid PatientDto patientDto,
                                        Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Patient patient = this.modelMapper.map(patientDto, Patient.class);
        Patient newPatient = this.patientRepository.save(patient);

        URI location = linkTo(PatientController.class).slash(newPatient.getId()).toUri();

        return ResponseEntity.created(location).body(newPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPatient(@PathVariable Long id) {
        Optional<Patient> findPatient = this.patientRepository.findById(id);

        if (findPatient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(findPatient.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePatient(@PathVariable Long id,
                                        @RequestBody @Valid PatientDto patientDto,
                                        Errors errors) {
        Optional<Patient> findPatient = this.patientRepository.findById(id);
        if (findPatient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Patient existingPatient = findPatient.get();
        this.modelMapper.map(patientDto, existingPatient);
        this.patientRepository.save(existingPatient);

        return ResponseEntity.ok(existingPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id) {
        Optional<Patient> findPatient = this.patientRepository.findById(id);
        if (findPatient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        this.patientRepository.deleteById(findPatient.get().getId());
        return ResponseEntity.ok().build();
    }
}
