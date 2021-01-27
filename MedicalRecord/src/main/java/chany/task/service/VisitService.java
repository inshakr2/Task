package chany.task.service;

import chany.task.domain.Patient;
import chany.task.domain.Visit;

import java.util.List;
import java.util.Optional;

public interface VisitService {
    public Long regist(Visit visit);
    public List<Visit> findAll();
    public Optional<Visit> findOne(Long visitId);
    public void deleteOne(Long visitId);
    public void modifyOne(Long visitId, Visit newVisit);
}
