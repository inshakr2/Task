package chany.task.MedicalRecord3.service.Impl;

import chany.task.MedicalRecord3.domain.Hospital;
import chany.task.MedicalRecord3.domain.Patient;
import chany.task.MedicalRecord3.domain.Visit;
import chany.task.MedicalRecord3.dto.VisitDto;
import chany.task.MedicalRecord3.repository.HospitalRepository;
import chany.task.MedicalRecord3.repository.PatientRepository;
import chany.task.MedicalRecord3.repository.VisitRepository;
import chany.task.MedicalRecord3.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;


    @Override
    public Visit registerVisit(VisitDto visitDto) {
        Hospital hospital = hospitalRepository.findById(visitDto.getHospitalId()).orElse(null);

        // 환자 ID 입력값이 없는 경우, 새로운 환자로 간주
        Patient newPatient = new Patient(hospital, visitDto.getPatientName(), "s",
                visitDto.getGenderCode(), visitDto.getBirth(), visitDto.getPhoneNumber());

        Patient visitPatient = patientRepository.save(newPatient);

        Visit visit = new Visit(hospital, visitPatient, visitDto.getVisitDate(), visitDto.getVisitCode());
        return visitRepository.save(visit);
    }

    @Override
    public Visit getVisit(Long id) {

        Visit visit = visitRepository.findById(id).orElse(null);
        if (visit == null) {
            throw new EntityNotFoundException("존재하지 않는 Visit입니다.");
        }

        return visit;
    }

    @Override
    public Visit updateVisit(Long id, VisitDto visitDto) {

        Visit visit = getVisit(id);

        visit.setHospital(hospitalRepository.findById(visitDto.getHospitalId()).orElse(null));
        //visit.setPatient(patientRepository.findById(visitDto.getPatientId()).orElse(null));
        visit.setRegisterDate(visitDto.getVisitDate());
        visit.setVisitCode(visitDto.getVisitCode());
        return visitRepository.save(visit);
    }

    @Override
    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }
}
