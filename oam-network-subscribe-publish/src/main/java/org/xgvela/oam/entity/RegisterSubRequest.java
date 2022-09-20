package org.xgvela.oam.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

@Data
@ApiModel(value = "RegisterSubRequest")
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSubRequest implements Serializable {
    private String systemId;
    private String callbackUrl;
}
