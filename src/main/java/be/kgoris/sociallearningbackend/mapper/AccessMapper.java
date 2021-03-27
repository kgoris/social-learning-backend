package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.AccessDto;
import be.kgoris.sociallearningbackend.entities.Access;

public interface AccessMapper {
    Access fromModelToDto(AccessDto accessDto);
    AccessDto fromDtoToModel(Access access);
}
