package chany.task.MedicalRecord5.controller;

import chany.task.MedicalRecord5.service.VisitService;
import chany.task.MedicalRecord5.domain.Visit;
import chany.task.MedicalRecord5.dto.VisitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/visit")
public class VisitController {

    private final VisitService visitService;

    @GetMapping("/{id}")
    public ResponseEntity getVisit(@PathVariable Long id) {
        Visit visit = visitService.getVisit(id);
        return ResponseEntity.ok(visit);
    }

    @PostMapping
    public ResponseEntity createVisit(@RequestBody @Valid VisitDto visitDto) {
        Visit visit = visitService.createVisit(visitDto);
        URI location = linkTo(VisitController.class).slash(visit.getId()).toUri();
        return ResponseEntity.created(location).body(visit);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateVisit(@PathVariable Long id,
                                      @RequestBody @Valid VisitDto visitDto) {
        Visit visit = visitService.updateVisit(id, visitDto);
        return ResponseEntity.ok(visit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
        return ResponseEntity.ok().build();
    }
}
