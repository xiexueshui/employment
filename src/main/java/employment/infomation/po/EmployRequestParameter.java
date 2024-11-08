package employment.infomation.po;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployRequestParameter extends Employ{

    @NotNull(message="size 不能为空")
    private Integer size;
    @NotNull(message="current 不能为空")
    private Integer current;

}
