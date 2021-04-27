package chany.task.service;

import chany.task.domain.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PatientService {

    public Long regist(Patient patient);
    public List<Patient> findAll();
    public Optional<Patient> findOne(Long patientId);
    public void deleteOne(Long patientId);
    public void modifyOne(Long patientId, Patient newPatient);

}
