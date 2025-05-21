package com.example.demo.convert;

import com.example.demo.entity.User;
import com.example.demo.entity.UserData;
import com.example.demo.entity.UserVo;
import org.mapstruct.*;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface UserConvert {

    UserData toDto(User userEntity);

    UserVo toVo(User userEntity);

    @AfterMapping
    default void toDtoAfterMapping(User source, @MappingTarget UserData target) {
        target.setName(source.getName() + new Date());
    }
    @AfterMapping
    default void toVoAfterMapping(User source, @MappingTarget UserVo target) {
        target.setFullName(source.getName() + " " + source.getAge());
    }


}