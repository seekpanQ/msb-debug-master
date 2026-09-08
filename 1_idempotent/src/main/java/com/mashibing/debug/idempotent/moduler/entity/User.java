package com.mashibing.debug.idempotent.moduler.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String idCard;
    private Integer age;
    private String email;
    @TableLogic
    private Integer isDelete;
}
