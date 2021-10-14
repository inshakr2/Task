package chany.task.MedicalRecord5.service;

import chany.task.MedicalRecord5.domain.Visit;
import chany.task.MedicalRecord5.dto.VisitDto;

public interface VisitService {

    Visit createVisit(VisitDto visitDto);

    Visit getVisit(Long id);

    Visit updateVisit(Long id, VisitDto visitDto);

    void deleteVisit(Long id);
}
