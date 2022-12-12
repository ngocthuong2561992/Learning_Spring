package com.springaop.algorithm.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class DebugHandler implements InvocationHandler {
    // original Object
    private Object target;

    public DebugHandler(Object t) {
        target = t;
    }

    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        // print implicit argument
        System.out.print(target);

        // print method name
        System.out.print("." + m.getName() + "(");

        // print explicit arguments
        if(args != null) {
            for(int i=0; i<args.length; i++) {
                System.out.print(args[i]);
                if(i<args.length - 1) System.out.println(", ");
            }
        }
        System.out.println(")");

        //invoke actual method
        return m.invoke(target, args);
    }
}
