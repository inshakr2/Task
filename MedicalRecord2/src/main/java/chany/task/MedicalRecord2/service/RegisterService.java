package chany.task.MedicalRecord2.service;

import chany.task.MedicalRecord2.domain.Patient;
import chany.task.MedicalRecord2.domain.Register;
import chany.task.MedicalRecord2.dto.QueryDto;

public interface RegisterService {

    Patient register(Register register);
    Patient update(Patient existingPatient, Register register);
}
