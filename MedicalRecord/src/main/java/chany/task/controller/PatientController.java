package chany.task.controller;

import chany.task.domain.Patient;
import chany.task.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/patients")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("")
    public List<Patient> getAllPatient() {
        return patientService.findAll();
    }

    @GetMapping("/{patientId}")
    public Optional<Patient> getPatientById(@PathVariable Long patientId) {
        return patientService.findOne(patientId);
    }

    @PostMapping("")
    public Long registPatient(@RequestBody Patient patient) {
        patientService.regist(patient);

        return patient.getPatientId();
    }

    @PutMapping("/{patientId}")
    public void modifyPatientById(@PathVariable Long patientId, @RequestBody Patient patient) {

        patientService.modifyOne(patientId, patient);
    }

    @DeleteMapping("/{patientId}")
    public void deleteById(@PathVariable Long patientId) {

        patientService.deleteOne(patientId);
    }
}
