package chany.task.MedicalRecord3.utils;

import chany.task.MedicalRecord3.domain.Patient;
import chany.task.MedicalRecord3.domain.PatientCodeSeq;

public class PatientKeyGenerator {

    // 병원ID + _ + now(YYMMdd) + Seq Entity
    public static String getPatientKey(Patient patient, PatientCodeSeq seq) {
        Long hospitalId = patient.getHospital().getId();
        String created = seq.getCreated();
        Long seqNo = seq.getSeq();

        String key = String.format("%d_%s%05d",
                              hospitalId, created, seqNo);
        return key;
    }
}