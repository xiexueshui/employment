package employment.infomation.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import employment.infomation.po.Employ;
import employment.infomation.po.EmployRequestParameter;
import employment.infomation.po.Result;
import employment.infomation.po.UserEmploy;
import employment.infomation.service.IUserEmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xxs
 * @since 2024-11-06
 */
@RestController
@RequestMapping("/userEmploy")
public class UserEmployController {

    @Autowired
    IUserEmployService userEmployService;
    @RequestMapping("/add")
    public Result add(@Validated @RequestBody UserEmploy userEmploy){
        userEmployService.save(userEmploy);
        return Result.ok();
    }
}
