package chany.task.MedicalRecord2.common;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {

            @Autowired
            ModelMapper modelMapper;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            }
        };
    }
}
