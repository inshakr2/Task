package chany.task.MedicalRecord4.service;

import chany.task.MedicalRecord4.domain.Patient;
import chany.task.MedicalRecord4.dto.PatientDto;
import chany.task.MedicalRecord4.dto.PatientQueryDto;
import chany.task.MedicalRecord4.dto.PatientSearchCondition;
import chany.task.MedicalRecord4.dto.RegisterDto;

import java.util.List;

public interface PatientService {

    Patient createPatient(PatientDto patientDto);

    Patient registerPatient(RegisterDto registerDto);

    Patient getPatient(Long id);

    List<PatientQueryDto> getPatients();
    List<PatientQueryDto> getPatientsByCondition(PatientSearchCondition condition);

    Patient updatePatient(Long id, PatientDto patientDto);

    void deletePatient(Long id);
}
