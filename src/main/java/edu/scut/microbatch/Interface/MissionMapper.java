package edu.scut.microbatch.Interface;

import edu.scut.microbatch.Mission;

public interface MissionMapper {
    //���뺯��������missionId
    Long insertMission(Mission mission);
    //����MissionId���Mission
    Mission getMission(Long missionId);
    //����Mission��״̬
    int updateMission(Long missionId,Mission mission);
}
