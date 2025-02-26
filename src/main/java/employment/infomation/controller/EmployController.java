package employment.infomation.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import employment.infomation.config.GuavaCache;
import employment.infomation.po.*;
import employment.infomation.service.IEmployService;
import employment.infomation.service.IUserEmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static employment.infomation.config.UserInterceptor.userThreadLocal;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xxs
 * @since 2024-11-06
 */
@RestController
@RequestMapping("/employ")
public class EmployController {

    @Autowired
    private IEmployService employService;
    @Autowired
    private IUserEmployService userEmployService;

    @RequestMapping("/list")
    public Result list(@Validated @RequestBody EmployRequestParameter employRequestParameter) {
        Page<Employ> page = new Page();
        page.setCurrent(employRequestParameter.getCurrent());
        page.setSize(employRequestParameter.getSize());


        Page<Employ> page1 = employService.page(page, Wrappers.lambdaQuery(Employ.class)
                .like(!StringUtils.isEmpty(employRequestParameter.getEmployName()), Employ::getEmployName, employRequestParameter.getEmployName())
                .eq(!StringUtils.isEmpty(employRequestParameter.getEducational()), Employ::getEducational, employRequestParameter.getEducational())
                .eq(!StringUtils.isEmpty(employRequestParameter.getMoneyType()), Employ::getMoneyType, employRequestParameter.getMoneyType())
                .eq(!StringUtils.isEmpty(employRequestParameter.getProfessional()), Employ::getProfessional, employRequestParameter.getProfessional())
                .eq(!StringUtils.isEmpty(employRequestParameter.getType()), Employ::getType, employRequestParameter.getType())
        );

        Integer userId = GuavaCache.getUserId(String.valueOf(userThreadLocal.get()));
        List<UserEmploy> userEmployList = userEmployService.list(Wrappers.lambdaQuery(UserEmploy.class).eq(UserEmploy::getUserId, userId));
        if (!CollectionUtils.isEmpty(userEmployList)) {
            Set<Integer> ids = userEmployList.stream().map(UserEmploy::getEmployId).collect(Collectors.toSet());
            page1.getRecords().forEach(e -> {
                if (ids.contains(e.getId())) {
                    e.setIsShowTo(true);
                }

            });
        }


        return Result.ok(page1);
    }


    @RequestMapping("/add")
    public Result add(@Validated @RequestBody Employ employ) {
        employService.save(employ);
        return Result.ok();
    }

    @RequestMapping("/del/{id}")
    public Result del(@PathVariable("id") Integer id) {
        employService.removeById(id);
        return Result.ok();
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Employ employ) {
        employService.updateById(employ);
        return Result.ok();
    }

    @RequestMapping("/myList")
    public Result myList(@Validated @RequestBody EmployRequestParameter employRequestParameter) {
        String token = userThreadLocal.get();
        Integer userId = GuavaCache.getUserId(token);
        List<UserEmploy> userEmploys = userEmployService.list(Wrappers.lambdaQuery(UserEmploy.class).eq(UserEmploy::getUserId, userId));
        if (CollectionUtils.isEmpty(userEmploys)) {
            return Result.ok(new Page<>());
        }

        Page page = new Page();
        page.setCurrent(employRequestParameter.getCurrent());
        page.setSize(employRequestParameter.getSize());


        Page reusult = employService.page(page, Wrappers.lambdaQuery(Employ.class)
                .in(Employ::getId, userEmploys.stream().map(UserEmploy::getEmployId).collect(Collectors.toSet()))
                .like(StringUtils.isEmpty(employRequestParameter.getEmployName()), Employ::getEmployName, employRequestParameter.getEmployName())
                .eq(!StringUtils.isEmpty(employRequestParameter.getEducational()), Employ::getEducational, employRequestParameter.getEducational())
                .eq(!StringUtils.isEmpty(employRequestParameter.getMoneyType()), Employ::getMoneyType, employRequestParameter.getMoneyType())
                .eq(!StringUtils.isEmpty(employRequestParameter.getProfessional()), Employ::getProfessional, employRequestParameter.getProfessional())
                .eq(!StringUtils.isEmpty(employRequestParameter.getType()), Employ::getType, employRequestParameter.getType())
                .orderBy(true, true, Employ::getId)
        );
        return Result.ok(reusult);
    }


}
