package chany.task.MedicalRecord5.service.Impl;

import chany.task.MedicalRecord5.domain.Patient;
import chany.task.MedicalRecord5.dto.PatientDto;
import chany.task.MedicalRecord5.dto.PatientQueryDto;
import chany.task.MedicalRecord5.dto.PatientResponseDto;
import chany.task.MedicalRecord5.dto.PatientSearchCondition;
import chany.task.MedicalRecord5.repository.PatientRepository;
import chany.task.MedicalRecord5.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<PatientResponseDto> getPatients() {
        List<Patient> allPatients = patientRepository.findAll();
        List<PatientResponseDto> responsePatients =
                allPatients.stream().map(m -> new PatientResponseDto(m)).
                        collect(Collectors.toList());

        return responsePatients;
    }

    @Override
    public List<PatientQueryDto> getPatientsByCondition(PatientSearchCondition condition,
                                                        int pageNo, int pageSize) {
        return patientRepository.searchByCondition(condition, pageNo, pageSize);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}