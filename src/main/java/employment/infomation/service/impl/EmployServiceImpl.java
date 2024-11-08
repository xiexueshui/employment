package employment.infomation.service.impl;

import employment.infomation.po.Employ;
import employment.infomation.mapper.EmployMapper;
import employment.infomation.service.IEmployService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xxs
 * @since 2024-11-06
 */
@Service
public class EmployServiceImpl extends ServiceImpl<EmployMapper, Employ> implements IEmployService {

}
