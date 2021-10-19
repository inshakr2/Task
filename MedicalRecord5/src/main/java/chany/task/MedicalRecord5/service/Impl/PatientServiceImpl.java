package chany.task.MedicalRecord5.service.Impl;

import chany.task.MedicalRecord5.domain.Patient;
import chany.task.MedicalRecord5.dto.PatientDto;
import chany.task.MedicalRecord5.repository.PatientRepository;
import chany.task.MedicalRecord5.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
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
    public Patient updatePatient(Long id, PatientDto patientDto) {
        Patient findPatient = getPatient(id);
        if (findPatient == null) {
            throw new EntityNotFoundException("존재하지 않는 회원입니다.");
        }

        findPatient.updatePatient(patientDto);

        return patientRepository.save(findPatient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}