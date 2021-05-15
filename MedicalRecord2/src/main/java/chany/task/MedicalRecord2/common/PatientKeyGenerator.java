package chany.task.MedicalRecord2.common;

import chany.task.MedicalRecord2.domain.Visit;

import java.time.format.DateTimeFormatter;

public class PatientKeyGenerator {

    public static String generate(Visit visit) {

        String visitTime =  visit.getDateTime().format(DateTimeFormatter.ofPattern("YYMMdd_HHmm"));
        Long hospitalId = visit.getHospital().getId();

        String key = String.format("%05d_%s", hospitalId, visitTime);

        return key;

    }
}
