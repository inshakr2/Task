package chany.task.MedicalRecord3.dto;

import chany.task.MedicalRecord3.domain.Patient;
import chany.task.MedicalRecord3.domain.Visit;
import com.querydsl.core.annotations.QueryProjection;
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

    @QueryProjection
    public PatientResponseDto(String name, String patientCode, String gender, String birth,
                              String phoneNumber, List<Visit> visits) {
        this.name = name;
        this.patientCode = patientCode;
        this.gender = getGender2Kor(gender);
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.latestVisit = getLatestVisit2Str(visits);
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
