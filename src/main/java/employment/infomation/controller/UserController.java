package employment.infomation.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import employment.infomation.config.GuavaCache;
import employment.infomation.po.Result;
import employment.infomation.po.User;
import employment.infomation.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static employment.infomation.config.UserInterceptor.userThreadLocal;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author xxs
 * @since 2024-11-06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/login")
    public Result login(@Validated @RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        User one = userService.getOne(Wrappers.lambdaQuery(User.class)
                .eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, encodeP(user.getPassword())));


        if (one == null) {
            return Result.faile(null, "用户或密码不正确");
        }

        String token = UUID.randomUUID().toString();
        result.put("userId", one.getId());
        result.put("token", token);
        result.put("userName", one.getUsername());
        result.put("avatar", one.getAvatar());
        result.put("role",0);
        result.put("isAdmin",one.getIsAdmin());
        GuavaCache.putToken(token, one.getId());
        return Result.ok(result);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        user.setPassword(encodeP(user.getPassword()));
        userService.save(user);
        return Result.ok();
    }


    @RequestMapping("/logout")
    public Result logout() {
        GuavaCache.remove(userThreadLocal.get());
        return Result.ok();
    }

    @RequestMapping("/update")
    public Result update(@RequestBody User user) {
        userService.updateById(user);
        return Result.ok();
    }

    @RequestMapping("/list")
    public Result list(@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        return Result.ok(userService.page(page));
    }

    public static String decodeP(String p) {
        return new String(Base64.getDecoder().decode(p.getBytes()));
    }

    public static String encodeP(String p) {
        return new String(Base64.getEncoder().encode(p.getBytes()));
    }


}
