package chany.task.MedicalRecord2.controller;

import chany.task.MedicalRecord2.domain.Visit;
import chany.task.MedicalRecord2.dto.VisitDto;
import chany.task.MedicalRecord2.repository.VisitRepository;
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
            return ResponseEntity.badRequest().build();
        }

        Visit visit = modelMapper.map(visitDto, Visit.class);
        Visit newVisit = this.visitRepository.save(visit);

        URI location = linkTo(VisitController.class).slash(newVisit.getId()).toUri();

        return ResponseEntity.created(location).body(newVisit);

    }

    @GetMapping("/{id}")
    public ResponseEntity getVisit(@PathVariable Long id) {

        Optional<Visit> findVisit = this.visitRepository.findById(id);

        if (findVisit.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Visit visit = findVisit.get();
            return ResponseEntity.ok(visit);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateVisit(@PathVariable Long id,
                                      @RequestBody @Valid VisitDto visitDto,
                                      Errors errors) {

        Optional<Visit> findVisit = this.visitRepository.findById(id);
        if (findVisit.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Visit existingVisit = findVisit.get();
        this.modelMapper.map(visitDto, existingVisit);
        Visit saveVisit = this.visitRepository.save(existingVisit);

        return ResponseEntity.ok(existingVisit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVisit(@PathVariable Long id) {
        Optional<Visit> findVisit = this.visitRepository.findById(id);

        if (findVisit.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        this.visitRepository.deleteById(findVisit.get().getId());
        return ResponseEntity.ok().build();
    }

}
