package io.graversen.minecraft.rcon.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.graversen.minecraft.rcon.Application;
import io.graversen.minecraft.rcon.commands.WhiteListCommand;
import io.graversen.minecraft.rcon.model.R;
import io.graversen.minecraft.rcon.model.User;
import io.graversen.minecraft.rcon.model.UserBindInfo;
import io.graversen.minecraft.rcon.service.UserService;
import io.graversen.minecraft.rcon.util.Target;
import io.graversen.minecraft.rcon.util.WhiteListModes;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Nott
 * @date 2024-10-28
 */

@Slf4j
@RestController
@RequestMapping("/rcon/")
public class RconWebController {

    @Resource
    private UserService userService;

    @PostMapping("bind/{gameName}/{code}")
    public R<?> bindWhiteList(@PathVariable("gameName") String gameName, @PathVariable("code") String code,@RequestBody @Valid UserBindInfo userBindInfo) {
        LambdaQueryWrapper<User> eq = new LambdaQueryWrapper<User>().eq(User::getGameName, gameName)
                .eq(User::getCode, code);
        User user = userService.getOne(eq);

        if(Objects.isNull(user)){
            return R.fail(String.format("GameName %s & code %s,not found", gameName,code));
        }

        Application.RCON_INSTANCE.sendAsync(new WhiteListCommand(Target.player(gameName), WhiteListModes.ADD));

        user.setBindingTime(new Date());
        user.setStatus(1);
        user.setQqNum(userBindInfo.getQqNum());
        userService.updateById(user);
        return R.ok();
    }
}
