package chany.task.MedicalRecord3.repository.init;

import chany.task.MedicalRecord3.domain.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class InitHospital {

    private final InitHospitalService initHospitalService;

    @PostConstruct
    public void init() {
        initHospitalService.initHospital();
    }

    @Component
    static class InitHospitalService {
        @PersistenceContext
        EntityManager em;

        @Transactional
        public void initHospital() {
            em.persist(new Hospital("A","A","A"));
            em.persist(new Hospital("B","B","B"));
            em.persist(new Hospital("C","C","C"));
            em.persist(new Hospital("D","D","D"));
            em.persist(new Hospital("E","E","E"));
            em.persist(new Hospital("F","F","F"));
            em.persist(new Hospital("G","G","G"));
        }
    }
}
