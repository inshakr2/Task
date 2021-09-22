package chany.task.MedicalRecord4.service;

import chany.task.MedicalRecord4.domain.Patient;
import chany.task.MedicalRecord4.dto.PatientDto;

public interface PatientService {

    Patient createPatient(PatientDto patientDto);

    Patient getPatient(Long id);

    Patient updatePatient(Long id, PatientDto patientDto);

    void deletePatient(Long id);
}
