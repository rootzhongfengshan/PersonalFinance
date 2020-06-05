package net.vv2.MyExceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {
    /*     @ResponseBody
         @ExceptionHandler(Exception.class)
         public Map<String,Object> handleException(Exception e){
             Map<String,Object> map = new HashMap<>();
             map.put("code","400");
             map.put("message",e.getMessage());
             return map;
         }*/
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码  4xx 5xx
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","500");
        map.put("message","出错啦");
        request.setAttribute("ext",map);
        //转发到/error
        return "forward:/error";
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleException(RuntimeException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "499");
        map.put("message", e.getMessage());
        return map;
    }
}
