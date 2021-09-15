package chany.task.MedicalRecord3.service.Impl;

import chany.task.MedicalRecord3.domain.Hospital;
import chany.task.MedicalRecord3.domain.Patient;
import chany.task.MedicalRecord3.domain.Visit;
import chany.task.MedicalRecord3.domain.PatientCodeSeq;
import chany.task.MedicalRecord3.dto.FirstVisit;
import chany.task.MedicalRecord3.dto.VisitDto;
import chany.task.MedicalRecord3.repository.HospitalRepository;
import chany.task.MedicalRecord3.repository.PatientCodeSeqRepository;
import chany.task.MedicalRecord3.repository.PatientRepository;
import chany.task.MedicalRecord3.repository.VisitRepository;
import chany.task.MedicalRecord3.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final PatientCodeSeqRepository codeSeqRepository;

    @Validated(FirstVisit.class)
    public Patient firstVisit(@Valid VisitDto visitDto, Hospital hospital) {

        String seqId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYMMdd"));
        PatientCodeSeq seq = codeSeqRepository.findById(seqId).orElse(null);

        if (seq == null) {
            seq = new PatientCodeSeq();
            codeSeqRepository.save(seq);
        } else {
            seq.countUp();
        }

        Patient patient = new Patient(hospital, visitDto.getPatientName(), visitDto.getGenderCode(),
                                    visitDto.getBirth(), visitDto.getPhoneNumber(), seq);
        return patientRepository.save(patient);
    }

    public Patient alreadyVisit(@Valid VisitDto visitDto) {
        Patient patient = patientRepository.findWithVisits(visitDto.getPatientId()).orElse(null);
        if (patient == null) {
            throw new EntityNotFoundException("존재하지 않는 환자입니다.");
        }
        return patient;
    }


    @Override
    @Transactional
    public Visit registerVisit(VisitDto visitDto) {

        Hospital hospital = hospitalRepository.findById(visitDto.getHospitalId()).orElse(null);
        if (hospital == null) {
            throw new EntityNotFoundException("존재하지 않는 병원입니다.");
        }

        // DTO에 환자 ID가 있는 경우, 기존 환자 조회하여 사용. else의 경우 새로 등록
        Patient visitPatient;
        if (visitDto.getPatientId() != null) {
            visitPatient = alreadyVisit(visitDto);
        } else {
            visitPatient = firstVisit(visitDto, hospital);

        }
        Visit visit = Visit.registerVisit(hospital, visitPatient, visitDto.getVisitDate(), visitDto.getVisitCode());
        return visitRepository.save(visit);
    }

    @Override
    public Visit getVisit(Long id) {

        Visit visit = visitRepository.findVisitEntityGraph(id).orElse(null);
        if (visit == null) {
            throw new EntityNotFoundException("존재하지 않는 방문입니다.");
        }

        return visit;
    }

    @Override
    @Transactional
    public Visit updateVisit(Long id, VisitDto visitDto) {

        Visit visit = getVisit(id);
        Hospital hospital = new Hospital();
        if (visit.getHospital().getId() == visitDto.getHospitalId()) {
            hospital = visit.getHospital();
        } else {
            hospital = hospitalRepository.findById(visitDto.getHospitalId()).orElse(null);
            if (hospital == null) {
                throw new EntityNotFoundException("존재하지 않는 병원입니다.");
            }
        }

        // Visit 수정의 경우, 이미 등록단계 자체에서 새로운 회원에 대한 등록 과정을 거쳤기때문에 patiendId 필드 외엔 무시함.
        if (visitDto.getPatientId() != null) {
            Patient patient = alreadyVisit(visitDto);
            visit.updateVisit(hospital, patient, visitDto.getVisitCode(), visitDto.getVisitDate());
        } else {
            visit.updateVisit(hospital, visitDto.getVisitCode(), visitDto.getVisitDate());
        }

        return visitRepository.save(visit);
    }

    @Override
    @Transactional
    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }
}
