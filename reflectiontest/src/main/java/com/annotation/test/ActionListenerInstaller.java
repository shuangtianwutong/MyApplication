package com.annotation.test;

import android.view.View;

import com.reflection.test.ActionListener;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ActionListenerInstaller {

    public static void processAnnotations(Object client) {
        Class<?> clientClass = client.getClass();

        for (Method m : clientClass.getDeclaredMethods()) {

            //获取指定Annotation对象
            ActionListener listener = m.getAnnotation(ActionListener.class);

            if (listener != null) {
                try {
                    Field f = clientClass.getDeclaredField(listener.source());
                    f.setAccessible(true);
                    //控件对象
                    Object focusView = f.get(client);
                    //addListenr函数添加监听，当click事件发生时，调用 onBtnClick() 函数
                    addListenr(focusView, client, m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private static void addListenr(final Object focusView, final Object client, final Method m) throws Exception {

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //场景类调用 onBtnClick() 方法
                return m.invoke(client);
            }
        };

        Object onClickListenr = Proxy.newProxyInstance(null, new Class[]{View.OnClickListener.class}, handler);

        Method setOnClickListenerMethod = focusView.getClass().getMethod("setOnClickListener", View.OnClickListener.class);

        setOnClickListenerMethod.invoke(focusView, onClickListenr);

    }

}
