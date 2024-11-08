package employment.infomation.service.impl;

import employment.infomation.po.User;
import employment.infomation.mapper.UserMapper;
import employment.infomation.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author xxs
 * @since 2024-11-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
