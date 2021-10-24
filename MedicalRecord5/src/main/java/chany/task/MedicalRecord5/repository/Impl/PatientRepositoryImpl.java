package chany.task.MedicalRecord5.repository.Impl;

import chany.task.MedicalRecord5.domain.Patient;
import chany.task.MedicalRecord5.dto.PatientQueryDto;
import chany.task.MedicalRecord5.dto.PatientSearchCondition;
import chany.task.MedicalRecord5.repository.PatientRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static chany.task.MedicalRecord5.domain.QPatient.patient;
import static chany.task.MedicalRecord5.domain.QVisit.visit;

public class PatientRepositoryImpl implements PatientRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PatientRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<PatientQueryDto> searchByCondition(PatientSearchCondition condition,
                                                   int pageNo, int pageSize) {

        List<Patient> results = queryFactory
                .selectFrom(patient).distinct()
                .leftJoin(patient.visits, visit)
                .where(
                        nameLike(condition.getName()),
                        codeLike(condition.getCode()),
                        birthLike(condition.getBirth())
                )
                .offset(pageNo)
                .limit(pageSize)
                .fetch();

        return results.stream()
                .map(p -> new PatientQueryDto(p.getPatientName(), p.getPatientCode(), p.getGenderCode(),
                        p.getBirth(), p.getPhoneNumber(), p.getVisits()))
                .collect(Collectors.toList());
    }

    private BooleanExpression nameLike(String name) {
        return StringUtils.hasText(name) ? patient.patientName.containsIgnoreCase(name) : null;
    }

    private BooleanExpression codeLike(String code) {
        return StringUtils.hasText(code) ? patient.patientCode.containsIgnoreCase(code) : null;
    }

    private BooleanExpression birthLike(String birth) {
        return StringUtils.hasText(birth) ? patient.birth.containsIgnoreCase(birth) : null;
    }
}