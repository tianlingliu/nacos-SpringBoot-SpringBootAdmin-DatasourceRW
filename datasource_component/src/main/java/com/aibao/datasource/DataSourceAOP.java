package com.aibao.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * 切面拦截请求执行的方法，动态切换线程需要的上下文依赖数据源
 */
@Aspect
@Component
public class  DataSourceAOP {

    @Pointcut("!@annotation(com.aibao.datasource.Master) " +
            "&& (execution(* com.aibao..*.mapper.*.select*(..)) " +
            "|| execution(* com.aibao..*.mapper..*.get*(..)))")
    public void readPointcut() {
    }

    @Pointcut("@annotation(com.aibao.datasource.Master) " +
            "|| execution(* com.aibao..*.mapper..*.insert*(..)) " +
            "|| execution(* com.aibao..*.mapper..*.add*(..)) " +
            "|| execution(* com.aibao..*.mapper..*.update*(..)) " +
            "|| execution(* com.aibao..*.mapper..*.edit*(..)) " +
            "|| execution(* com.aibao..*.mapper..*.delete*(..)) " +
            "|| execution(* com.aibao..*.mapper..*.remove*(..))")
    public void writePointcut() {
    }

    @Before("readPointcut()")
    public void read() {
        DatabaseContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DatabaseContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.aibaoxian.auth.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DatabaseContextHolder.slave();
//        }else {
//            DatabaseContextHolder.master();
//        }
//    }
}
