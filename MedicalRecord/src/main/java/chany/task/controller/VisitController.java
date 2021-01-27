package chany.task.controller;

import chany.task.domain.Visit;
import chany.task.service.VisitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/visits")
public class VisitController {

    private VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("")
    public List<Visit> getAllVisit() {
        return visitService.findAll();
    }

    @GetMapping("/{visitId}")
    public Optional<Visit> getVisitById(@PathVariable Long visitId) {
        return visitService.findOne(visitId);
    }

    @PostMapping("")
    public Long registVisit(@RequestBody Visit visit) {
        visitService.regist(visit);
        return visit.getVisitId();
    }

    @PutMapping("/{visitId}")
    public void modifyVisitById(@PathVariable Long visitId, @RequestBody Visit visit) {
        visitService.modifyOne(visitId, visit);
    }

    @DeleteMapping("/{visitId}")
    public void deleteById(@PathVariable Long visitId) {
        visitService.deleteOne(visitId);

    }
}
