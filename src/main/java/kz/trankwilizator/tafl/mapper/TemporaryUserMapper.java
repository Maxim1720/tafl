package kz.trankwilizator.tafl.mapper;

import kz.trankwilizator.tafl.dto.TemporaryUserDto;
import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemporaryUserMapper {
    TemporaryUserDto toDto(TemporaryUser temporaryUser);
}
