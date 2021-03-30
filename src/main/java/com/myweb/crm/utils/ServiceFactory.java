package com.myweb.crm.utils;

public class ServiceFactory {
    public static Object getservice(Object service){
        return  new TransactionInvocationHandler(service).getProxy();
    }
}
