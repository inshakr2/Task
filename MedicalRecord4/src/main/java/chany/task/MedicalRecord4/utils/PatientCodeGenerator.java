package chany.task.MedicalRecord4.utils;

import chany.task.MedicalRecord4.domain.Patient;
import chany.task.MedicalRecord4.domain.PatientCodeSeq;

public class PatientCodeGenerator {

    public static String getCode(Patient patient, PatientCodeSeq seq) {
        int visitSize = patient.getVisits().size();
        Long hospitalId = patient.getVisits().get(visitSize - 1).getHospital().getId();
        String created = seq.getCreated();
        Long seqNo = seq.getSeq();

        String code = String.format("%d_%s%05d",
                hospitalId, created, seqNo);
        return code;
    }
}
