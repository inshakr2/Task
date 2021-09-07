package chany.task.MedicalRecord3.service;

import chany.task.MedicalRecord3.domain.Patient;
import chany.task.MedicalRecord3.dto.PatientDto;

public interface PatientService {

    Patient savePatient(PatientDto patientDto);

    Patient getPatient(Long id);

    Patient updatePatient(Long id, PatientDto patientDto);

    void deletePatient(Long id);
}
