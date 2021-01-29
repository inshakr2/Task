package chany.task.service.impl;

import chany.task.domain.Visit;
import chany.task.repository.VisitRepository;
import chany.task.service.VisitService;

import java.util.List;
import java.util.Optional;

public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    /**
     * 입력받는 Visit 등록
     * @param visit
     * @return 등록된 VisitId
     */
    @Override
    public Long regist(Visit visit) {
        visitRepository.save(visit);
        return visit.getVisitId();
    }

    /**
     * 모든 Visit List 조회
     * @return Visit List
     */
    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    /**
     * ID를 입력 값으로 Visit 조회
     * @param visitId
     * @return 조회결과 Visit
     */
    @Override
    public Optional<Visit> findOne(Long visitId) {
        return visitRepository.findById(visitId);
    }

    /**
     * ID를 입력 값으로 Visit 삭제
     * @param visitId
     */
    @Override
    public void deleteOne(Long visitId) {
        visitRepository.deleteById(visitId);

    }

    /**
     * ID와 새로운 Visit를 입력받아 입력받은 Visit의 Data를 Set
     * @param visitId
     * @param newVisit
     */
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
