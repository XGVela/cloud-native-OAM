package org.xgvela.oam.entity.conf;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FileTree implements Serializable {
    private Integer id;
    private Integer pId;
    private String name;
    private Boolean isDirectory;
    private Boolean isModify;
    private String size;
    private Date createdTime;
}
