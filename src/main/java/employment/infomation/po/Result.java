package employment.infomation.po;

import lombok.Data;

@Data
public class Result<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;
    private T data;

    public static <T> Result<T> ok(T t){
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMessage("success");
        result.setData(t);
        return result;
    }
    public static <T> Result<T> ok(){
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMessage("");
        return result;
    }

    public static <T> Result<T> faile(T t,String message){
        Result<T> result = new Result<>();
        result.setCode(1);
        result.setMessage(message);
        result.setData(t);
        return result;
    }

}
