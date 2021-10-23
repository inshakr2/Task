package chany.task.MedicalRecord5.repository;

import chany.task.MedicalRecord5.dto.PatientQueryDto;
import chany.task.MedicalRecord5.dto.PatientSearchCondition;

import java.util.List;

public interface PatientRepositoryCustom {
    List<PatientQueryDto> searchByCondition(PatientSearchCondition condition);
}
