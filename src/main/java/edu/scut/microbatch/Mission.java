package edu.scut.microbatch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mission {
    private Long missionId;
    private String topicName;
    private String missionState;
    private Date createDate;
}
