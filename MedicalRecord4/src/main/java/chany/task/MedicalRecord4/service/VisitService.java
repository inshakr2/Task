package chany.task.MedicalRecord4.service;

import chany.task.MedicalRecord4.domain.Visit;
import chany.task.MedicalRecord4.dto.VisitDto;

public interface VisitService {

    Visit createVisit(VisitDto visitDto);

    Visit getVisit(Long id);

    Visit updateVisit(Long id, VisitDto visitDto);

    void deleteVisit(Long id);
}
