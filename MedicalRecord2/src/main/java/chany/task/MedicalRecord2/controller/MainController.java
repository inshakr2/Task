package chany.task.MedicalRecord2.controller;

import chany.task.MedicalRecord2.domain.Patient;
import chany.task.MedicalRecord2.domain.Register;
import chany.task.MedicalRecord2.dto.RegisterDto;
import chany.task.MedicalRecord2.dto.UpdateRegisterDto;
import chany.task.MedicalRecord2.repository.PatientRepository;
import chany.task.MedicalRecord2.repository.VisitRepository;
import chany.task.MedicalRecord2.service.RegisterService;
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
@RequestMapping(value = "/api/main/patients", produces = MediaTypes.HAL_JSON_VALUE)
public class MainController {

    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final RegisterService registerService;
    private final ModelMapper modelMapper;

    public MainController(PatientRepository patientRepository, VisitRepository visitRepository, RegisterService registerService, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.registerService = registerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity registerPatient(@RequestBody @Valid RegisterDto registerDto,
                                             Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Register registerInfo = this.modelMapper.map(registerDto, Register.class);
        Patient createdPatient = this.registerService.register(registerInfo);

        URI location = linkTo(MainController.class).slash(createdPatient.getId()).toUri();

        return ResponseEntity.created(location).body(createdPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePatient(@PathVariable Long id,
                                        @RequestBody @Valid UpdateRegisterDto updateRegisterDto,
                                        Errors errors) {

        Optional<Patient> findPatient = this.patientRepository.findById(id);
        if (findPatient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Patient existingPatient = findPatient.get();
        Register register = this.modelMapper.map(updateRegisterDto, Register.class);
        Patient updatedPatient = this.registerService.update(existingPatient, register);

        return ResponseEntity.ok(updatedPatient);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id) {
        Optional<Patient> findPatient = this.patientRepository.findById(id);

        if (findPatient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        this.patientRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }



}
