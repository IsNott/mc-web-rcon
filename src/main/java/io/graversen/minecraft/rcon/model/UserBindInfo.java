package io.graversen.minecraft.rcon.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;



/**
 * @author Nott
 * @date 2024-10-28
 */
@Data
public class UserBindInfo {

    @NotNull
    private Long qqNum;
}
