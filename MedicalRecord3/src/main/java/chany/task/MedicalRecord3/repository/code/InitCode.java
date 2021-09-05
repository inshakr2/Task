package chany.task.MedicalRecord3.repository.code;

import chany.task.MedicalRecord3.domain.code.Code;
import chany.task.MedicalRecord3.domain.code.CodeGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class InitCode {

    private final InitCodeService initCodeService;

    @PostConstruct
    public void init() {
        initCodeService.initCodeGroup();
        initCodeService.initCode();
    }

    @Component
    static class InitCodeService {
        @PersistenceContext
        EntityManager em;

        @Transactional
        public void initCodeGroup() {
            em.persist(new CodeGroup("성별코드", "성별코드", "성별을 표시"));
            em.persist(new CodeGroup("방문상태코드", "방문상태코드", "환자방문의 상태(방문중, 종료, 취소)"));
            em.persist(new CodeGroup("진료과목코드", "진료과목코드", "진료과목(내과, 안과 등)"));
            em.persist(new CodeGroup("진료유형코드", "진료유형코드", "진료의 유형(약처방, 검사 등)"));
        }

        @Transactional
        public void initCode() {
            em.persist(new Code("M", "남", em.find(CodeGroup.class, "성별코드")));
            em.persist(new Code("F", "여", em.find(CodeGroup.class, "성별코드")));
            em.persist(new Code("1", "방문중", em.find(CodeGroup.class, "방문상태코드")));
            em.persist(new Code("2", "종료", em.find(CodeGroup.class, "방문상태코드")));
            em.persist(new Code("3", "취소", em.find(CodeGroup.class, "방문상태코드")));
            em.persist(new Code("01", "내과", em.find(CodeGroup.class, "진료과목코드")));
            em.persist(new Code("02", "안과", em.find(CodeGroup.class, "진료과목코드")));
            em.persist(new Code("D", "약처방", em.find(CodeGroup.class, "진료유형코드")));
            em.persist(new Code("T", "검사", em.find(CodeGroup.class, "진료유형코드")));
        }
    }



}
