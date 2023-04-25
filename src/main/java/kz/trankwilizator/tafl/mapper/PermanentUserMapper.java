package kz.trankwilizator.tafl.mapper;

import kz.trankwilizator.tafl.dto.PermanentUserDto;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermanentUserMapper {
    PermanentUserDto toDto(PermanentUser permanentUser);
    PermanentUser toEntity(PermanentUserDto user);
}
