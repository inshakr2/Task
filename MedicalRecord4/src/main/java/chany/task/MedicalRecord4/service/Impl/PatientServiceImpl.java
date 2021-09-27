package chany.task.MedicalRecord4.service.Impl;

import chany.task.MedicalRecord4.domain.Hospital;
import chany.task.MedicalRecord4.domain.Patient;
import chany.task.MedicalRecord4.domain.PatientCodeSeq;
import chany.task.MedicalRecord4.domain.Visit;
import chany.task.MedicalRecord4.dto.PatientDto;
import chany.task.MedicalRecord4.dto.PatientQueryDto;
import chany.task.MedicalRecord4.dto.PatientSearchCondition;
import chany.task.MedicalRecord4.dto.RegisterDto;
import chany.task.MedicalRecord4.dto.marker.AlreadyVisit;
import chany.task.MedicalRecord4.dto.marker.FirstVisit;
import chany.task.MedicalRecord4.repository.HospitalRepository;
import chany.task.MedicalRecord4.repository.PatientCodeSeqRepository;
import chany.task.MedicalRecord4.repository.PatientRepository;
import chany.task.MedicalRecord4.repository.VisitRepository;
import chany.task.MedicalRecord4.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final VisitRepository visitRepository;
    private final PatientCodeSeqRepository seqRepository;

    @Validated(FirstVisit.class)
    public Patient firstVisit(@Valid RegisterDto registerDto) {

        Hospital hospital = hospitalRepository.findById(registerDto.getHospitalId()).orElse(null);
        if (hospital == null) {
            throw new EntityNotFoundException("존재하지 않는 병원입니다.");
        }

        Visit visit = Visit.createVisit(hospital, registerDto);
        visitRepository.save(visit);
        Patient patient = Patient.registerPatient(registerDto, visit, getSeq());

        return patient;
    }

    @Validated(AlreadyVisit.class)
    public Patient alreadyVisit(@Valid RegisterDto registerDto) {
        Patient patient = getPatient(registerDto.getPatientId());

        Hospital hospital = hospitalRepository.findById(registerDto.getHospitalId()).orElse(null);
        if (hospital == null) {
            throw new EntityNotFoundException("존재하지 않는 병원입니다.");
        }

        Visit visit = Visit.createVisit(hospital, registerDto);
        visitRepository.save(visit);
        patient.addVisit(visit);

        if (patient.getPatientCode() == "-") {
            patient.registerPatientCode(getSeq());
        }
        return patient;
    }

    private PatientCodeSeq getSeq() {
        String seqId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYMMdd"));
        PatientCodeSeq seq = seqRepository.findById(seqId).orElse(null);

        if (seq == null) {
            seq = new PatientCodeSeq();
            seqRepository.save(seq);
        } else {
            seq.countUp();
        }
        return seq;
    }

    @Override
    @Transactional
    public Patient registerPatient(RegisterDto registerDto) {
        Hospital hospital = hospitalRepository.findById(registerDto.getHospitalId()).orElse(null);
        if (hospital == null) {
            throw new EntityNotFoundException("존재하지 않는 병원입니다.");
        }

        Patient patient;
        if (registerDto.getPatientId() != null) {
            patient = alreadyVisit(registerDto);
        } else {
            patient = firstVisit(registerDto);
        }

        return patientRepository.save(patient);
    }

    @Override
    @Transactional
    public Patient createPatient(PatientDto patientDto) {
        Patient patient = Patient.createPatient(patientDto);

        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(Long id) {
        Patient patient = patientRepository.findWithVisits(id).orElse(null);
        if (patient == null) {
            throw new EntityNotFoundException("존재하지 않는 회원입니다.");
        }

        return patient;
    }

    @Override
    public List<PatientQueryDto> getPatients() {
        List<Patient> patients = patientRepository.findAllWithVisits();
        List<PatientQueryDto> results = new ArrayList<>();

        for (Patient p : patients) {
            results.add(new PatientQueryDto(p.getPatientName(),
                                            p.getPatientCode(),
                                            p.getGenderCode(),
                                            p.getBirth(),
                                            p.getPhoneNumber(),
                                            p.getVisits()));
        }
        return results;
    }

    @Override
    public List<PatientQueryDto> getPatientsByCondition(PatientSearchCondition condition,
                                                        int pageNo, int pageSize) {
        return patientRepository.searchByCondition(condition, pageNo, pageSize);
    }

    @Override
    @Transactional
    public Patient updatePatient(Long id, PatientDto patientDto) {
        Patient findPatient = getPatient(id);
        if (findPatient == null) {
            throw new EntityNotFoundException("존재하지 않는 회원입니다.");
        }

        findPatient.updatePatient(patientDto);

        return patientRepository.save(findPatient);
    }

    @Override
    @Transactional
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
