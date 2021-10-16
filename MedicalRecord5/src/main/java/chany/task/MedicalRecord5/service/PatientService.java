package chany.task.MedicalRecord5.service;

import chany.task.MedicalRecord5.domain.Patient;
import chany.task.MedicalRecord5.dto.PatientDto;

public interface PatientService {

    Patient createPatient(PatientDto patientDto);

    Patient getPatient(Long id);

    Patient updatePatient(Long id, PatientDto patientDto);

    void deletePatient(Long id);
}
