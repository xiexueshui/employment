package employment.infomation.config;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {
    public static ThreadLocal<String> userThreadLocal = new ThreadLocal<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURL().toString().endsWith("html")) {
            return true;
        }

        String token = getToken(request);
        if(token == null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "用户未登录");
            return false;
        }
        userThreadLocal.set(getToken(request));
        return true;
    }

    private String getToken(HttpServletRequest request) {
        Object token = request.getHeader("token");
        if(token == null){
            return null;
        }
        if (GuavaCache.getUserId(String.valueOf(token)) == null) {
            return null;
        }
        return String.valueOf(token);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        userThreadLocal.remove();
    }


}
