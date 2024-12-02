package employment.infomation.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author xxs
 * @since 2024-12-01
 */
@Getter
@Setter
public class Ranking implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 省份
     */
    private String province;

    /**
     * 层次
     */
    private String level;

    /**
     * 科类
     */
    private String type;

    /**
     * 最高分数
     */
    private String maxScore;

    /**
     * 最低分数
     */
    private String minScore;

    /**
     * 分数区间
     */
    private String scoreBet;

    /**
     * 位次
     */
    private Integer rankNum;

    /**
     * 同分人数
     */
    private Integer sameNum;
}
