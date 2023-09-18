package com.skarva.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long taskId;
    private String taskName;
    private String taskStatus;
    private String type;
    private Timestamp created;
    private Timestamp updated;

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", type='" + type + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
