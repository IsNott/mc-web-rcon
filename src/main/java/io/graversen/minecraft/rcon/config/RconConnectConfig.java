package io.graversen.minecraft.rcon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Nott
 * @date 2024-10-28
 */
@Data
@Component
@ConfigurationProperties(prefix = "remote")
public class RconConnectConfig {

    private String host;

    private Integer port;

    private String password;

}
