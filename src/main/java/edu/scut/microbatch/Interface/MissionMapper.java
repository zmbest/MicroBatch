package edu.scut.microbatch.Interface;

import edu.scut.microbatch.Mission;

public interface MissionMapper {
    //插入函数、返回missionId
    Long insertMission(Mission mission);
    //根据MissionId获得Mission
    Mission getMission(Long missionId);
    //更新Mission的状态
    int updateMission(Long missionId,Mission mission);
}
