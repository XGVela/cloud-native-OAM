package org.xgvela.oam.entity.alarm.analysis;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(autoResultMap = true)
public class LogFilter {

    public String logField;

    public String operator;

    public String valueType;

    public String activeField;

    public String fixedValue;
}
