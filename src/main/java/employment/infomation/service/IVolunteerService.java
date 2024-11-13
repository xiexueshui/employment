package employment.infomation.service;

import employment.infomation.mapper.VolunteerMapper;
import employment.infomation.po.Volunteer;
import com.baomidou.mybatisplus.extension.service.IService;
import employment.infomation.po.VolunteerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xxs
 * @since 2024-11-13
 */
public interface IVolunteerService extends IService<Volunteer> {


    List<String> getSpecialtyList(VolunteerRequest volunteerRequest);
}
