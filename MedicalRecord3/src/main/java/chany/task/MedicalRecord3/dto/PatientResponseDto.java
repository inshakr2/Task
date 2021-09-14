package chany.task.MedicalRecord3.dto;

import chany.task.MedicalRecord3.domain.Patient;
import chany.task.MedicalRecord3.domain.Visit;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class PatientResponseDto {
    private String name;
    private String patientCode;
    private String gender;
    private String birth;
    private String phoneNumber;
    private String latestVisit;

    public PatientResponseDto(Patient patient) {
        this.name = patient.getPatientName();
        this.patientCode = patient.getPatientCode();
        this.gender = getGender2Kor(patient.getGenderCode());
        this.birth = patient.getBirth();
        this.phoneNumber = patient.getPhoneNumber();
        this.latestVisit = getLatestVisit2Str(patient.getVisits());
    }

    private String getGender2Kor(String gender) {
        switch (gender.toUpperCase()) {
            case "M": gender = "남자";
                break;
            case "F": gender = "여자";
                break;
            default: gender = "모름";
                break;
        }
        return gender;
    }

    private String getLatestVisit2Str(List<Visit> visits) {
        if (visits.size() != 0) {
            Visit latestVisit = visits.get(visits.size() -1);
            return latestVisit.getRegisterDate().format(DateTimeFormatter.ofPattern(("YYYY-MM-dd")));
        } else {
            return "-";
        }
    }
}
