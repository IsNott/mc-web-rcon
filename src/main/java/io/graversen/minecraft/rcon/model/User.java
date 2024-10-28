package io.graversen.minecraft.rcon.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Nott
 * @date 2024-10-28
 */
@Data
@TableName("user")
public class User {

    @TableId("id")
    private Long id;

    private String gameName;

    private Long qqNum;

    private String code;

    private Date creatTime;

    private Date bindingTime;

    private Integer status;

}
