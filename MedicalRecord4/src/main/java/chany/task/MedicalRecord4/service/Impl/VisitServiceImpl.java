package chany.task.MedicalRecord4.service.Impl;

import chany.task.MedicalRecord4.domain.Hospital;
import chany.task.MedicalRecord4.domain.Visit;
import chany.task.MedicalRecord4.dto.VisitDto;
import chany.task.MedicalRecord4.repository.HospitalRepository;
import chany.task.MedicalRecord4.repository.VisitRepository;
import chany.task.MedicalRecord4.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    @Transactional
    public Visit createVisit(VisitDto visitDto) {
        Hospital hospital = hospitalRepository.findById(visitDto.getHospitalId()).orElse(null);
        if (hospital == null) {
            throw new EntityNotFoundException("존재하지 않는 병원입니다.");
        }

        Visit visit = Visit.createVisit(hospital, visitDto);
        return visitRepository.save(visit);
    }

    @Override
    public Visit getVisit(Long id) {

        Visit visit = visitRepository.findById(id).orElse(null);

        if (visit == null) {
            throw new EntityNotFoundException("존재하지 않는 방문기록입니다.");
        }

        return visit;
    }

    @Override
    @Transactional
    public Visit updateVisit(Long id, VisitDto visitDto) {

        Visit visit = getVisit(id);
        if (visit == null) {
            throw new EntityNotFoundException("존재하지 않는 방문 기록입니다.");
        }

        if (visit.getHospital().getId() == visitDto.getHospitalId()) {
            visit.updateVisit(visitDto);
        } else {
            Hospital hospital = hospitalRepository.findById(visitDto.getHospitalId()).orElse(null);
            if (hospital == null) {
                throw new EntityNotFoundException("존재하지 않는 병원입니다.");
            }
            visit.updateVisit(hospital, visitDto);
        }

        return visitRepository.save(visit);

    }

    @Override
    @Transactional
    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }
}
