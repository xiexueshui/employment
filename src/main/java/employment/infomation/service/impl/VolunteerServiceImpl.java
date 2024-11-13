package employment.infomation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import employment.infomation.po.Volunteer;
import employment.infomation.mapper.VolunteerMapper;
import employment.infomation.po.VolunteerRequest;
import employment.infomation.service.IVolunteerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xxs
 * @since 2024-11-13
 */
@Service
public class VolunteerServiceImpl extends ServiceImpl<VolunteerMapper, Volunteer> implements IVolunteerService {

    @Autowired
    VolunteerMapper volunteerMapper;
    @Override
    public List<String> getSpecialtyList(VolunteerRequest volunteerRequest) {
        LambdaQueryWrapper<Volunteer> queryWrapper = Wrappers.lambdaQuery(Volunteer.class)
                .select(Volunteer::getSpecialtyName) // 选择 specialtyName 字段
                .like(!Objects.isNull(volunteerRequest.getSpecialtyName()),Volunteer::getSpecialtyName,volunteerRequest.getSpecialtyName())
                .groupBy(Volunteer::getSpecialtyName);
        return volunteerMapper.selectObjs(queryWrapper);
    }
}
