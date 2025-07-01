package org.agent.MedAgent.Object;

import org.agent.MedAgent.Object.Result;

public class Response {
    public static Result<Void> success(){
        Result<Void> result = new Result<>();
        result.setCode(200);
        result.setMessage("成功");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    public static Result<Void> error(int code, String message) {
        Result<Void> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}

