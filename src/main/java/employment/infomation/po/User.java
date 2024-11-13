package employment.infomation.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author xxs
 * @since 2024-11-06
 */
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户账号
     */
    @NotNull(message = "用户账号不能为空")
    private String username;

    /**
     * 用户密码
     */
    @NotNull(message = "用户密码不能为空")
    private String password;

    /**
     * 用户电话
     */
    private String mobile;

    /**
     * 用户性别: [1=男, 2=女]
     */
    private String sex;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private String educational;

    private String userDes;

    private Integer isAdmin;

    @TableField(exist = false)
    private String token ;

    private String category;
}
