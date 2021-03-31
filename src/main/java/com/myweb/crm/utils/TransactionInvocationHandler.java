package com.myweb.crm.utils;
import org.apache.ibatis.session.SqlSession;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionInvocationHandler implements InvocationHandler {
    private Object target;
    public TransactionInvocationHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession session=null;
        Object obj=null;
        try {
            session =SqlSessionUtil.getsession();
//            处理业务逻辑
            obj=method.invoke(target,args);
//            处理业务逻辑完毕后，提交事务
            session.commit();
        }catch (Exception e){
            session.rollback();
//            向上给controller抛异常 处理的什么异常 抛的就是什么异常
            e.printStackTrace();
            throw e.getCause();
        }finally {
            SqlSessionUtil.Myclose(session);
        }
        return obj;
    }
//    取得代理类对象
    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
}
