package chany.task.service;

import chany.task.domain.Patient;
import chany.task.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Long regist(Patient patient) {
        patientRepository.save(patient);
        return patient.getPatientId();
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findOne(Long patientId) {
        return patientRepository.findById(patientId);
    }

    @Override
    public void deleteOne(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public void modifyOne(Long patientId, Patient newPatient) {
        Optional<Patient> oldPatient = patientRepository.findById(patientId);

        oldPatient.ifPresent(patient -> {
            patient.setName(newPatient.getName());
            patient.setBirth(newPatient.getBirth());
            patient.setGenderCode(newPatient.getGenderCode());
            patient.setPhoneNumber(newPatient.getPhoneNumber());
            patient.setRegistNumber(newPatient.getRegistNumber());
            patientRepository.save(patient);
        });
    }

}
