package chany.task.MedicalRecord4.dto;

import lombok.Data;

@Data
public class PatientQueryDto {
    private String name;
    private String patientCode;
    private String gender;
    private String birth;
    private String phoneNumber;
    private String latestVisit;
}
