package org.xgvela.oam.service.alarm;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.xgvela.oam.entity.alarm.active.AlarmRecord;
import org.xgvela.oam.mapper.alarm.active.AlarmRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class AlarmRecordServiceImpl extends ServiceImpl<AlarmRecordMapper, AlarmRecord> implements IAlarmRecordService {

}
