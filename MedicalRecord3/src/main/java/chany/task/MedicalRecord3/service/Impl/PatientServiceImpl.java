package chany.task.MedicalRecord3.service.Impl;

import chany.task.MedicalRecord3.domain.Hospital;
import chany.task.MedicalRecord3.domain.Patient;
import chany.task.MedicalRecord3.dto.PatientDto;
import chany.task.MedicalRecord3.repository.HospitalRepository;
import chany.task.MedicalRecord3.repository.PatientRepository;
import chany.task.MedicalRecord3.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    @Transactional
    public Patient savePatient(PatientDto patientDto) {
        Hospital hospital = hospitalRepository.findById(patientDto.getHospitalId()).orElse(null);

        Patient newPatient = new Patient(hospital, patientDto.getName(), "s",
                patientDto.getGenderCode(), patientDto.getBirth(), patientDto.getPhoneNumber());

        return patientRepository.save(newPatient);
    }

    @Override
    public Patient getPatient(Long id) {

        Patient patient = patientRepository.findPatientEntityGraph(id).orElse(null);
        if (patient == null) {
            throw new EntityNotFoundException("존재하지 않는 회원입니다.");
        }

        return patient;
    }

    @Override
    @Transactional
    public Patient updatePatient(Long id, PatientDto patientDto) {
        Patient patient = getPatient(id);

        // 기존 병원 ID값과 비교해서 다를 경우에만 update
        if (patient.getHospital().getId() == patientDto.getHospitalId()) {
            patient.updatePatient(patientDto);
        } else {
            Hospital hospital = hospitalRepository.findById(patientDto.getHospitalId()).orElseThrow();
            patient.updatePatient(patientDto, hospital);
        }

        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);

    }
}
