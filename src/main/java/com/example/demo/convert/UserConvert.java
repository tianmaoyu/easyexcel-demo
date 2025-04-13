package com.example.demo.convert;

import com.example.demo.entity.User;
import com.example.demo.entity.UserData;
import com.example.demo.entity.UserVo;
import org.mapstruct.*;

import java.util.Date;

@Mapper
public abstract class UserConvert {

    public abstract UserData toDto(User userEntity);

    @AfterMapping
    protected void toDtoAfterMapping(User source, @MappingTarget UserData target) {
        target.setName(source.getName() + new Date());
    }

    public abstract UserVo toVo(User userEntity);

    @AfterMapping
    protected void toVoAfterMapping(User source, @MappingTarget UserVo target) {
        target.setFullName(source.getName() + " " + source.getAge());
    }

}