package chany.task.MedicalRecord2.common;

import chany.task.MedicalRecord2.domain.Hospital;
import chany.task.MedicalRecord2.domain.Visit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatientKeyGenerator {

    public static String generate(Long hospitalId, Visit visit) {

        String visitTime =  visit.getDateTime().format(DateTimeFormatter.ofPattern("YYMMdd_HHmm"));

        String key = String.format("%05d_%s", hospitalId, visitTime);

        return key;

    }
}
