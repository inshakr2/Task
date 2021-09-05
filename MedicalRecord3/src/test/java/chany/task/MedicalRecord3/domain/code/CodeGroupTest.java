package chany.task.MedicalRecord3.domain.code;

import chany.task.MedicalRecord3.repository.code.CodeGroupRepository;
import chany.task.MedicalRecord3.repository.code.CodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CodeGroupTest {

    @Autowired
    CodeGroupRepository codeGroupRepository;
    @Autowired
    CodeRepository codeRepository;

    @Test
    public void codeTest() {

        CodeGroup codeGroup = new CodeGroup("테스트코드", "테스트코드", "테스트코드");
        codeGroupRepository.save(codeGroup);
        Code code = new Code("TEST", "TEST", codeGroup);
        codeRepository.save(code);

        Code findCode = codeRepository.findById("TEST").get();
        CodeGroup findCodeGroup = codeGroupRepository.findById("테스트").get();

        assertThat(findCode.getCode()).isEqualTo(code.getCode());
        assertThat(findCodeGroup.getCodeGroup()).isEqualTo(codeGroup.getCodeGroup());

    }
}