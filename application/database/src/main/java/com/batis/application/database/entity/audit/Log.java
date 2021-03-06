package com.batis.application.database.entity.audit;

import com.batis.application.database.entity.base.NoSqlEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "log")
public class Log extends NoSqlEntity{

    private String level;

    private String module;

    private String message;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
