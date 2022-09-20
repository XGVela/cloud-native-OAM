package org.xgvela.oam.entity.alarm.analysis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @author baihuilei
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm_analysis_response_field")
public class AlarmAnalysisResponseField extends Model<AlarmAnalysisResponseField> implements Serializable {
    private static final long serialVersionUID = -2241039999114233754L;

    @ApiModelProperty(value = "id",hidden = true)
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("alarm_id")
    public String alarmId;

    @TableField("parent")
    public String parent;

    @TableField("children")
    public String childes;

    @TableField(exist = false)
    public Set<String> children;

    @ApiModelProperty(value = "status")
    @TableField("status")
    private Integer status;
}
