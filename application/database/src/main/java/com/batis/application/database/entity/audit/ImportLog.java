package com.batis.application.database.entity.audit;

import com.batis.application.database.entity.base.NoSqlEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "import_log")
public class ImportLog extends NoSqlEntity {
    private String fileName;
    private String fileDigest;
    private LocalDateTime processAt;
    private String processStatus;
    private LocalDateTime finishedAt;
    private String message;
    private String source;
    private String target;
    private String comment;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDigest() {
        return fileDigest;
    }

    public void setFileDigest(String fileDigest) {
        this.fileDigest = fileDigest;
    }

    public LocalDateTime getProcessAt() {
        return processAt;
    }

    public void setProcessAt(LocalDateTime processAt) {
        this.processAt = processAt;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
