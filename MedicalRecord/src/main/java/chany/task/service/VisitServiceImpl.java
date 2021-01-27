package chany.task.service;

import chany.task.domain.Visit;
import chany.task.repository.VisitRepository;

import java.util.List;
import java.util.Optional;

public class VisitServiceImpl implements VisitService{

    private final VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Long regist(Visit visit) {
        visitRepository.save(visit);
        return visit.getVisitId();
    }

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    @Override
    public Optional<Visit> findOne(Long visitId) {
        return visitRepository.findById(visitId);
    }

    @Override
    public void deleteOne(Long visitId) {
        visitRepository.deleteById(visitId);

    }

    @Override
    public void modifyOne(Long visitId, Visit newVisit) {
        Optional<Visit> oldVisit = visitRepository.findById(visitId);

        oldVisit.ifPresent(visit -> {
            visit.setVisitCode(newVisit.getVisitCode());
            visit.setReceiptDate(newVisit.getReceiptDate());
            visitRepository.save(visit);
        });
    }
}
