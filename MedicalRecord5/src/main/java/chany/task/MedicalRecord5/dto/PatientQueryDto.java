package chany.task.MedicalRecord5.dto;

import chany.task.MedicalRecord5.domain.Visit;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class PatientQueryDto {
    private String name;
    private String patientCode;
    private String gender;
    private String birth;
    private String phoneNumber;
    private String latestVisit;

    @QueryProjection
    public PatientQueryDto(String name, String patientCode, String gender,
                           String birth, String phoneNumber, List<Visit> visits) {
        this.name = name;
        this.patientCode = patientCode;
        this.gender = getGender2Kor(gender);
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.latestVisit = getLatestVisit2Str(visits);
    }

    private String getGender2Kor(String genderCode) {
        switch (genderCode.toUpperCase()) {
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