package chany.task.MedicalRecord2.service;

import chany.task.MedicalRecord2.domain.Patient;
import chany.task.MedicalRecord2.domain.Register;

public interface RegisterService {

    Patient register(Register register);
}
