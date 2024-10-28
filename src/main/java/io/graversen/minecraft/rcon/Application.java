package io.graversen.minecraft.rcon;

import io.graversen.minecraft.rcon.config.RconConnectConfig;
import io.graversen.minecraft.rcon.service.ConnectOptions;
import io.graversen.minecraft.rcon.service.MinecraftRconService;
import io.graversen.minecraft.rcon.service.RconDetails;
import io.graversen.minecraft.rcon.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.time.Duration;

/**
 * @author Nott
 * @date 2024-10-25
 */

@Slf4j
@SpringBootApplication(scanBasePackages = "io.graversen.minecraft.rcon")
@MapperScan("io.graversen.minecraft.rcon.mapper")
public class Application implements ApplicationListener<ContextClosedEvent> {

    public static MinecraftRcon RCON_INSTANCE;

    public static MinecraftRconService minecraftRconService;

    public static MinecraftRcon getRconInstance() {
        return RCON_INSTANCE;
    }

    public static MinecraftRconService getMinecraftRconService() {
        return minecraftRconService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        RconConnectConfig config = SpringContextUtil.getBean(RconConnectConfig.class);
        log.info("Web Application started");
        final MinecraftRconService minecraftRconService = new MinecraftRconService(
                new RconDetails(config.getHost(),config.getPort(), config.getPassword()),
                ConnectOptions.defaults()
        );
        minecraftRconService.connectBlocking(Duration.ofSeconds(5));
        RCON_INSTANCE = minecraftRconService.minecraftRcon().orElseThrow(IllegalStateException::new);
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        MinecraftRcon rconInstance = getRconInstance();
        MinecraftRconService rconService = getMinecraftRconService();
        if(rconInstance != null && rconService != null){
            rconService.disconnect();
        }
    }
}
