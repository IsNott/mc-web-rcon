package io.graversen.minecraft.rcon;

import io.graversen.minecraft.rcon.service.ConnectOptions;
import io.graversen.minecraft.rcon.service.MinecraftRconService;
import io.graversen.minecraft.rcon.service.RconDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

/**
 * @author Nott
 * @date 2024-10-25
 */

@SpringBootApplication(scanBasePackages = "io.graversen.minecraft.rcon")
@Slf4j
public class Application {

    public static MinecraftRcon minecraftRcon;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        log.info("Web Application started");
        final MinecraftRconService minecraftRconService = new MinecraftRconService(
                RconDetails.localhost(),
                ConnectOptions.defaults()
        );
        minecraftRconService.connectBlocking(Duration.ofSeconds(5));
        minecraftRcon = minecraftRconService.minecraftRcon().orElseThrow(IllegalStateException::new);
    }
}
