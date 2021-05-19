package chany.task.MedicalRecord2.common;

import chany.task.MedicalRecord2.domain.Visit;
import chany.task.MedicalRecord2.dto.RegisterDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class RegisterValidator {

    public void validate(RegisterDto registerDto, List<Visit> visits, Errors errors) {
        Optional<LocalDateTime> opt = Optional.ofNullable(registerDto.getVisitTime());

        if (! visits.contains(opt.get())) {
            errors.reject("bad request", "visit time is not changeable");
        }
    }
}
