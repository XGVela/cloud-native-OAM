package org.xgvela.oam.entity.alarm.analysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseField {
    public List<String> parent;
    public List<String> children;
}
