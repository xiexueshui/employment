package employment.infomation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import employment.infomation.po.Ranking;
import employment.infomation.po.Result;
import employment.infomation.po.Volunteer;
import employment.infomation.po.VolunteerRequest;
import employment.infomation.service.IRankingService;
import employment.infomation.service.IVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xxs
 * @since 2024-11-13
 */
@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
    @Autowired
    IVolunteerService volunteerService;

    @Autowired
    IRankingService rankingService;


    @RequestMapping("/page")
    public Result page(@RequestBody VolunteerRequest volunteerRequest) {
        Page page = new Page();
        page.setSize(volunteerRequest.getSize());
        page.setCurrent(volunteerRequest.getCurrent());
        Page page1 = volunteerService.page(page, Wrappers.lambdaQuery(Volunteer.class)
                .eq(!StringUtils.isEmpty(volunteerRequest.getYear()),Volunteer::getYear, volunteerRequest.getYear())
                .like(!StringUtils.isEmpty(volunteerRequest.getCollegeName()),Volunteer::getCollegeName, volunteerRequest.getCollegeName())
                .eq(!StringUtils.isEmpty(volunteerRequest.getSpecialtyName()),Volunteer::getSpecialtyName, volunteerRequest.getSpecialtyName())
                .orderByDesc(Volunteer::getLowestScore));
        return Result.ok(page1);
    }


    /**
     * 专业列表
     * @param volunteerRequest
     * @return
     */

    @RequestMapping("/specialtyList")
    public Result specialtyList(@RequestBody VolunteerRequest volunteerRequest) {

        return Result.ok(volunteerService.getSpecialtyList(volunteerRequest));
    }


    @RequestMapping("/add")
    public Result add(@RequestBody Volunteer volunteer) {
        return Result.ok(volunteerService.save(volunteer));
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Volunteer volunteer) {
        return Result.ok(volunteerService.updateById(volunteer));
    }

    @RequestMapping("/del/{id}")
    public Result del(@PathVariable("id") String id) {
        return Result.ok(volunteerService.removeById(id));
    }


    @RequestMapping("/recommend/{year}/{type}/{score}")
    public Result recommend(@PathVariable("year") String yearS,@PathVariable("type") String type,@PathVariable("score") String scoreString) {
        Integer score =  Integer.parseInt(scoreString);
        Integer year =  Integer.parseInt(yearS);
        Map<String,Object> result = new HashMap<>();
        Ranking ranking = rankingService.getOne(Wrappers.lambdaQuery(Ranking.class)
                        .eq(Ranking::getYear,year)
                .eq(Ranking::getType,type).le(Ranking::getMaxScore, score).ge(Ranking::getMinScore, score));

        List<Volunteer> list = volunteerService.list(Wrappers.lambdaQuery(Volunteer.class).ge(Volunteer::getLowestScore, score)
                .eq(Volunteer::getYear, year)
                .orderBy(true, true, Volunteer::getLowestScore).last("LIMIT 50"));

        result.put("num",Objects.isNull(ranking)?0:ranking.getRankNum());
        result.put("list",list);

        return Result.ok(result);

    }



}
