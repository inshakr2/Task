package chany.task.MedicalRecord2.controller;

import chany.task.MedicalRecord2.repository.VisitRepository;
import org.springframework.stereotype.Controller;

@Controller
public class VisitController {

    private final VisitRepository visitRepository;

    public VisitController(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }


}
