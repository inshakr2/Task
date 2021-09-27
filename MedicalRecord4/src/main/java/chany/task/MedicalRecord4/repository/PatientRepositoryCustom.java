package chany.task.MedicalRecord4.repository;

import chany.task.MedicalRecord4.dto.PatientQueryDto;
import chany.task.MedicalRecord4.dto.PatientSearchCondition;

import java.util.List;

public interface PatientRepositoryCustom {
    List<PatientQueryDto> searchByCondition(PatientSearchCondition condition,
                                            int pageNo, int pageSize);
}
