package com.example.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component // 确保被 Spring 管理
public class AutoFillHandler implements MetaObjectHandler {

    // 插入时触发填充
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createId", Long.class, 10L);
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateId", Long.class, 10L);
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    // 更新时触发填充
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateId", Long.class, 10L);
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}