package chany.task.MedicalRecord2.service.impl;

import chany.task.MedicalRecord2.common.PatientKeyGenerator;
import chany.task.MedicalRecord2.domain.Hospital;
import chany.task.MedicalRecord2.domain.Patient;
import chany.task.MedicalRecord2.domain.Register;
import chany.task.MedicalRecord2.domain.Visit;
import chany.task.MedicalRecord2.dto.RegisterDto;
import chany.task.MedicalRecord2.repository.HospitalRepository;
import chany.task.MedicalRecord2.repository.PatientRepository;
import chany.task.MedicalRecord2.repository.VisitRepository;
import chany.task.MedicalRecord2.service.RegisterService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final VisitRepository visitRepository;
    private final ModelMapper modelMapper;

    public RegisterServiceImpl(PatientRepository patientRepository, HospitalRepository hospitalRepository, VisitRepository visitRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
        this.visitRepository = visitRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Patient register(Register register) {

        Hospital hospital = register.getHospital();
        this.hospitalRepository.save(hospital);

        Visit visit = register.getVisit();
        this.visitRepository.save(visit);

        Patient patient = register.getPatient();
        patient.setHospital(hospital);
        patient.setCode(PatientKeyGenerator.generate(hospital.getId(), visit));
        patient.getVisits().add(visit);
        this.patientRepository.save(patient);

        return patient;
    }

    @Override
    public Patient update(Patient existingPatient, Register register) {

        Hospital updatedHospital = register.getHospital();
        Patient updatePatient = register.getPatient();

        this.modelMapper.map(updatedHospital, existingPatient.getHospital());
        this.hospitalRepository.save(existingPatient.getHospital());

        this.modelMapper.map(updatePatient, existingPatient);
        this.patientRepository.save(existingPatient);

        return existingPatient;
    }
}
