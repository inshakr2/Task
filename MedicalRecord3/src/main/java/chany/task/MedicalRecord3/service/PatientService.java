package chany.task.MedicalRecord3.service;

import chany.task.MedicalRecord3.domain.Patient;
import chany.task.MedicalRecord3.dto.PatientDto;
import chany.task.MedicalRecord3.dto.PatientResponseDto;

import java.util.List;

public interface PatientService {

    Patient savePatient(PatientDto patientDto);

    Patient getPatient(Long id);

    List<PatientResponseDto> getPatients();

    Patient updatePatient(Long id, PatientDto patientDto);

    void deletePatient(Long id);
}
