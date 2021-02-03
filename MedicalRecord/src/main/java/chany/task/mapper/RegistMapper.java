package chany.task.mapper;

import chany.task.DTO.RegistPatientDto;
import chany.task.domain.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegistMapper {

    RegistMapper mapper = Mappers.getMapper(RegistMapper.class);

    @Mapping(target = "registNumber")
    Patient dtoToEntity(RegistPatientDto registPatientDto);

    @Mapping(target = "visitCode")
    RegistPatientDto entityToDto(Patient patient);
}
