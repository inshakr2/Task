package chany.task.MedicalRecord5.service;

import chany.task.MedicalRecord5.domain.Patient;
import chany.task.MedicalRecord5.dto.PatientDto;
import chany.task.MedicalRecord5.dto.PatientQueryDto;
import chany.task.MedicalRecord5.dto.PatientResponseDto;

import java.util.List;

public interface PatientService {

    Patient createPatient(PatientDto patientDto);

    Patient getPatient(Long id);

    List<PatientResponseDto> getPatients();

    Patient updatePatient(Long id, PatientDto patientDto);

    void deletePatient(Long id);
}
