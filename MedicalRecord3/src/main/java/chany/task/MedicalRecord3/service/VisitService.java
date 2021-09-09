package chany.task.MedicalRecord3.service;

import chany.task.MedicalRecord3.domain.Visit;
import chany.task.MedicalRecord3.dto.PatientDto;
import chany.task.MedicalRecord3.dto.VisitDto;

public interface VisitService {

    Visit registerVisit(VisitDto visitDto);

    Visit getVisit(Long id);

    Visit updateVisit(Long id, VisitDto visitDto);

    void deleteVisit(Long id);
}
