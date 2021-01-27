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

    /**
     * 입력받는 Patient 등록
     * @param patient
     * @return 등록된 patientId
     */
    @Override
    public Long regist(Patient patient) {
        patientRepository.save(patient);
        return patient.getPatientId();
    }

    /**
     * 모든  Patient List 조회
     * @return Patient List
     */
    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    /**
     * ID를 입력 값으로 Patient 조회
     * @param patientId
     * @return 조회 결과 Patient
     */
    @Override
    public Optional<Patient> findOne(Long patientId) {
        return patientRepository.findById(patientId);
    }

    /**
     * ID를 입력 값으로 Patient 삭제
     * @param patientId
     */
    @Override
    public void deleteOne(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    /**
     * ID와 새로운 Patient를 입력받아 입력받은 Patient의 Data를 Set
     * @param patientId
     * @param newPatient
     */
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
