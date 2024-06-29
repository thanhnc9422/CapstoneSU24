package fpt.CapstoneSU24.mapper;

import fpt.CapstoneSU24.dto.UserVerifyDTOResponse;
import fpt.CapstoneSU24.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

    @Mapper
    public interface UserMapper {
        UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

        UserVerifyDTOResponse userToUserDTO(User user);

        List<UserVerifyDTOResponse> usersToUserDTOs(List<User> users);
    }

