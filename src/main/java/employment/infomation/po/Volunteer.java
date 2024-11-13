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
 * @since 2024-11-13
 */
@Getter
@Setter
public class Volunteer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 院校编码
     */
    private String collegeCode;

    /**
     * 院校名称
     */
    private String collegeName;

    /**
     * 专业编码
     */
    private String specialtyCode;

    /**
     * 专业名称
     */
    private String specialtyName;

    /**
     * 投档最低分
     */
    private Integer lowestScore;

    /**
     * 语数分数
     */
    private Integer langNum;

    /**
     * 语文最高成绩
     */
    private Integer langNumHighest;

    private Integer foreignLang;

    private Integer firstScore;

    private Integer secondScore;

    private Integer lastScore;

    private Integer volunteerCode;

    private String year;
}
