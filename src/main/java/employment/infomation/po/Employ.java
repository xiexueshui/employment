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
 * 
 * </p>
 *
 * @author xxs
 * @since 2024-11-06
 */
@Getter
@Setter
public class Employ implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 职业类型
     */
    private Integer type;

    /**
     * 职业名称
     */
    private String employName;

    /**
     * 职业介绍
     */
    private String employDes;

    /**
     * 职业图片
     */
    private String employImg;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 专业
     */
    private String professional;

    /**
     * 学历
     */
    private String educational;

    /**
     * 薪资类型
     */
    private String moneyType;

    @TableField(exist = false)
    private Boolean isShowTo = false;
}
