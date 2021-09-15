package chany.task.MedicalRecord3.repository.Impl;

import chany.task.MedicalRecord3.domain.Patient;
import chany.task.MedicalRecord3.dto.PatientResponseDto;
import chany.task.MedicalRecord3.dto.PatientSearchCondition;
import chany.task.MedicalRecord3.repository.PatientRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static chany.task.MedicalRecord3.domain.QPatient.patient;
import static chany.task.MedicalRecord3.domain.QVisit.visit;

public class PatientRepositoryImpl implements PatientRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PatientRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<PatientResponseDto> searchByCondition(PatientSearchCondition condition) {

        List<Patient> patients = queryFactory
                .selectFrom(patient).distinct()
                .leftJoin(patient.visits, visit)
                .where(
                    nameLike(condition.getName()),
                    regiLike(condition.getRegiNo()),
                    birthLike(condition.getBirth()))
                .fetch();

        return patients.stream()
                .map(p -> new PatientResponseDto(p.getPatientName(), p.getPatientCode(), p.getGenderCode(),
                                                p.getBirth(), p.getPhoneNumber(), p.getVisits()))
                .collect(Collectors.toList());

    }

    private BooleanExpression nameLike(String name) {
        return StringUtils.hasText(name) ? patient.patientName.containsIgnoreCase(name) : null;
    }

    private BooleanExpression regiLike(String regi) {
        return StringUtils.hasText(regi) ? patient.patientCode.containsIgnoreCase(regi) : null;
    }

    private BooleanExpression birthLike(String birth) {
        return StringUtils.hasText(birth) ? patient.birth.containsIgnoreCase(birth) : null;
    }
}
