package chany.task.MedicalRecord3.controller;

import chany.task.MedicalRecord3.domain.Visit;
import chany.task.MedicalRecord3.dto.VisitDto;
import chany.task.MedicalRecord3.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @GetMapping("/visit/{id}")
    public ResponseEntity getVisit(@PathVariable Long id) {
        Visit visit = visitService.getVisit(id);
        return ResponseEntity.ok(visit);
    }

    @PostMapping("/visit")
    public ResponseEntity createVisit(@RequestBody @Valid VisitDto visitDto) {
        Visit visit = visitService.registerVisit(visitDto);
        URI location = linkTo(VisitController.class).slash(visit.getId()).toUri();
        return ResponseEntity.created(location).body(visit);
    }

    @PatchMapping("/visit/{id}")
    public ResponseEntity updateVisit(@PathVariable Long id,
                                      @RequestBody @Valid VisitDto visitDto) {
        Visit visit = visitService.updateVisit(id, visitDto);
        return ResponseEntity.ok(visit);
    }

    @DeleteMapping("/visit/{id}")
    public ResponseEntity deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
        return ResponseEntity.ok().build();
    }
}