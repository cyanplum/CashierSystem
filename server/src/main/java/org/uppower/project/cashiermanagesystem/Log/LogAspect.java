package org.uppower.project.cashiermanagesystem.Log;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.uppower.project.cashiermanagesystem.model.UserInfo;
import org.uppower.project.cashiermanagesystem.service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 *
 * @date 2019/10/2219:41
 */
@Aspect
@Component
public class LogAspect {


    @Autowired
    LogService logService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(org.uppower.project.cashiermanagesystem.Log.MyLog)")
    public void logPointCut() {
    }

    //切面 配置通知
    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint){
        System.out.println("切面。。。。。。");
        //保存日志
        Log log = new Log();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            log.setOperation(value);//保存获取的操作
        }
        //System.out.println(log.getOperation());

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        //Log.setMethod(className + "." + methodName);
        //System.out.println(methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        UserInfo userInfo = (UserInfo) args[0];
        //将参数所在的数组转换成json
        /*for (Object re:args){
            System.out.println(re);
        }*/
        //String params = JSON.toJSONString(args);

        //System.out.println(params);
        //sysLog.setParams(params);

        //获取时间
        //log.setCreateTime(new LocalDateTime().);
        //获取用户名
        //sysLog.setUsername(ShiroUtils.getUserEntity().getUsername());
        //获取用户ip地址
        //HttpServletRequest request = getHttpServletRequest();
        //sysLog.setIp(IPUtils.getIpAddr(request));

        //调用service保存SysLog实体类到数据库
        //sysLogService.save(sysLog);

        log.setUserId(userInfo.getUserId());
        logService.store(log);
    }
    private HttpServletRequest getHttpServletRequest(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

}
