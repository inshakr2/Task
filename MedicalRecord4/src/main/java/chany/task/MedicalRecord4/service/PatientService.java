package chany.task.MedicalRecord4.service;

import chany.task.MedicalRecord4.domain.Patient;
import chany.task.MedicalRecord4.dto.PatientDto;
import chany.task.MedicalRecord4.dto.RegisterDto;

public interface PatientService {

    Patient createPatient(PatientDto patientDto);

    Patient registerPatient(RegisterDto registerDto);

    Patient getPatient(Long id);

    Patient updatePatient(Long id, PatientDto patientDto);

    void deletePatient(Long id);
}
