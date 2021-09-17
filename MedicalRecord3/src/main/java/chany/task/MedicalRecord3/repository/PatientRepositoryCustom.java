package chany.task.MedicalRecord3.repository;

import chany.task.MedicalRecord3.dto.PatientResponseDto;
import chany.task.MedicalRecord3.dto.PatientSearchCondition;

import java.util.List;

public interface PatientRepositoryCustom {
    List<PatientResponseDto> searchByCondition(PatientSearchCondition condition,
                                               int pageNo, int pageSize);
}
