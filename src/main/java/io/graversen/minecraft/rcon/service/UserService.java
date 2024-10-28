package io.graversen.minecraft.rcon.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.graversen.minecraft.rcon.mapper.UserMapper;
import io.graversen.minecraft.rcon.model.User;
import org.springframework.stereotype.Service;

/**
 * @author Nott
 * @date 2024-10-28
 */

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
