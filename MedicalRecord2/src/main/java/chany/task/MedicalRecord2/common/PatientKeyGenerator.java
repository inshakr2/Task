package chany.task.MedicalRecord2.common;

import chany.task.MedicalRecord2.domain.Hospital;
import chany.task.MedicalRecord2.domain.Visit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatientKeyGenerator {

    public static String generate(Visit visit) {

        String visitTime =  visit.getDateTime().format(DateTimeFormatter.ofPattern("YYMMdd_HHmm"));
        Long hospitalId = visit.getHospital().getId();

        String key = String.format("%05d_%s", hospitalId, visitTime);

        return key;

    }

    public static String generate(LocalDateTime currentTime, Hospital hospital) {

        String visitTime =  currentTime.format(DateTimeFormatter.ofPattern("YYMMdd_HHmm"));
        Long hospitalId = hospital.getId();

        String key = String.format("%05d_%s", hospitalId, visitTime);

        return key;
    }
}
