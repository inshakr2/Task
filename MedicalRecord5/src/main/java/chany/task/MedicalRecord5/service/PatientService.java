package chany.task.MedicalRecord5.service;

import chany.task.MedicalRecord5.domain.Patient;
import chany.task.MedicalRecord5.dto.PatientDto;
import chany.task.MedicalRecord5.dto.PatientQueryDto;

import java.util.List;

public interface PatientService {

    Patient createPatient(PatientDto patientDto);

    Patient getPatient(Long id);

    List<PatientQueryDto> getPatients();

    Patient updatePatient(Long id, PatientDto patientDto);

    void deletePatient(Long id);
}
