package chany.task.MedicalRecord2.controller;

import chany.task.MedicalRecord2.domain.Patient;
import chany.task.MedicalRecord2.domain.Visit;
import chany.task.MedicalRecord2.dto.PatientDto;
import chany.task.MedicalRecord2.dto.VisitDto;
import chany.task.MedicalRecord2.repository.PatientRepository;
import chany.task.MedicalRecord2.repository.VisitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/main/patients", produces = MediaTypes.HAL_JSON_VALUE)
public class MainController {

    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final ModelMapper modelMapper;

    public MainController(PatientRepository patientRepository, VisitRepository visitRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity registerPatient(@RequestBody @Valid PatientDto patientDto,
                                          Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Patient registPatient = this.modelMapper.map(patientDto, Patient.class);
        Visit currentVisit = this.modelMapper.map(new VisitDto(), Visit.class);
        this.patientRepository.save(registPatient);
        this.visitRepository.save(currentVisit);

        registPatient.getVisits().add(currentVisit);
//        registPatient.register(currentVisit);

        URI location = linkTo(MainController.class).slash(registPatient.getId()).toUri();

        return ResponseEntity.created(location).body(registPatient);
    }
}
