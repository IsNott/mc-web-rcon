package io.graversen.minecraft.rcon.examples;

import io.graversen.minecraft.rcon.MinecraftRcon;
import io.graversen.minecraft.rcon.commands.PlayerListCommand;
import io.graversen.minecraft.rcon.commands.execute.ExecuteCommand;
import io.graversen.minecraft.rcon.commands.tellraw.TellRawCommand;
import io.graversen.minecraft.rcon.commands.tellraw.TellRawCommandBuilder;
import io.graversen.minecraft.rcon.service.ConnectOptions;
import io.graversen.minecraft.rcon.service.MinecraftRconService;
import io.graversen.minecraft.rcon.service.RconDetails;
import io.graversen.minecraft.rcon.util.Colors;
import io.graversen.minecraft.rcon.util.Selectors;

import java.time.Duration;

/**
 * @author Nott
 * @date 2024-10-25
 */
public class Example {

    public static void main(String[] args) {
        final MinecraftRconService minecraftRconService = new MinecraftRconService(
                RconDetails.localhost(),
                ConnectOptions.defaults()
        );

        minecraftRconService.connectBlocking(Duration.ofSeconds(5));

        final MinecraftRcon minecraftRcon = minecraftRconService.minecraftRcon().orElseThrow(IllegalStateException::new);

        minecraftRcon.sendAsync(PlayerListCommand.names());
    }
}
