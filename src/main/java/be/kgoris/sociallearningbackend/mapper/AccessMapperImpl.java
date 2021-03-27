package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.AccessDto;
import be.kgoris.sociallearningbackend.entities.Access;
import org.springframework.stereotype.Service;

@Service
public class AccessMapperImpl implements AccessMapper{
    @Override
    public Access fromModelToDto(AccessDto accessDto) {
        return null;
    }

    @Override
    public AccessDto fromDtoToModel(Access access) {
        return null;
    }
}
