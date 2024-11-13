package employment.infomation.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import employment.infomation.config.GuavaCache;
import employment.infomation.po.Result;
import employment.infomation.po.User;
import employment.infomation.po.UserParameter;
import employment.infomation.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
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
        one.setToken(token);
        GuavaCache.putToken(token, one.getId());
        return Result.ok(one);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        user.setPassword(encodeP(user.getPassword()));
        if(StringUtils.isEmpty(user.getAvatar())){
            user.setAvatar("data:image/png;base64,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");
        }
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
        User userVo = userService.getById(user.getId());
        userVo.setToken(userThreadLocal.get());
        return Result.ok(userVo);
    }

    @RequestMapping("/del/{id}")
    public Result del(@PathVariable("id") Integer id) {
        userService.removeById(id);
        return Result.ok();
    }


    @RequestMapping("/uploadImage")
    public Result uploadImage(MultipartFile file) {

        return Result.ok(generateBase64(file));
    }

    public static String generateBase64(MultipartFile file){
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("图片不能为空！");
        }
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        String contentType = file.getContentType();
        byte[] imageBytes = null;
        String base64EncoderImg="";
        try {
            imageBytes = file.getBytes();
            BASE64Encoder base64Encoder =new BASE64Encoder();
            /**
             * 1.Java使用BASE64Encoder 需要添加图片头（"data:" + contentType + ";base64,"），
             *   其中contentType是文件的内容格式。
             * 2.Java中在使用BASE64Enconder().encode()会出现字符串换行问题，这是因为RFC 822中规定，
             *   每72个字符中加一个换行符号，这样会造成在使用base64字符串时出现问题，
             *   所以我们在使用时要先用replaceAll("[\\s*\t\n\r]", "")解决换行的问题。
             */
            base64EncoderImg = "data:" + contentType + ";base64," + base64Encoder.encode(imageBytes);
            base64EncoderImg = base64EncoderImg.replaceAll("[\\s*\t\n\r]", "");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return base64EncoderImg;
    }

    @RequestMapping("/list")
    public Result list(@RequestBody UserParameter userParameter) {
        Page page = new Page();
        page.setCurrent(userParameter.getCurrent());
        page.setSize(userParameter.getSize());
        return Result.ok(userService.page(page,Wrappers.lambdaQuery(User.class).eq(!StringUtils.isEmpty(userParameter.getSex()),User::getSex,userParameter.getSex())
                .like(!StringUtils.isEmpty(userParameter.getUsername()),User::getUsername,userParameter.getUsername())));
    }

    public static String decodeP(String p) {
        return new String(Base64.getDecoder().decode(p.getBytes()));
    }

    public static String encodeP(String p) {
        return new String(Base64.getEncoder().encode(p.getBytes()));
    }


}
