package chany.task.MedicalRecord2.controller;

import chany.task.MedicalRecord2.domain.Visit;
import chany.task.MedicalRecord2.dto.VisitDto;
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
@RequestMapping(value = "/api/visits", produces = MediaTypes.HAL_JSON_VALUE)
public class VisitController {

    private final VisitRepository visitRepository;
    private final ModelMapper modelMapper;

    public VisitController(VisitRepository visitRepository, ModelMapper modelMapper) {
        this.visitRepository = visitRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity createVisit(@RequestBody @Valid VisitDto visitDto,
                                      Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Visit visit = modelMapper.map(visitDto, Visit.class);
        Visit newVisit = this.visitRepository.save(visit);

        URI location = linkTo(VisitController.class).slash(newVisit.getId()).toUri();

        return ResponseEntity.created(location).body(newVisit);

    }


}
