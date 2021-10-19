package chany.task.MedicalRecord5.service.Impl;

import chany.task.MedicalRecord5.service.VisitService;
import chany.task.MedicalRecord5.domain.Hospital;
import chany.task.MedicalRecord5.domain.Visit;
import chany.task.MedicalRecord5.dto.VisitDto;
import chany.task.MedicalRecord5.repository.HospitalRepository;
import chany.task.MedicalRecord5.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;

    @Override
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
    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }
}
