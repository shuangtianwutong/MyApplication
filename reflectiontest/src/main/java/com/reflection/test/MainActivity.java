package com.reflection.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button  button;

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.test);
        button =findViewById(R.id.button);
        //ActionListenerInstaller.processAnnotations(this);

    }
//    @ActionListener(source = "test_btn")
//    public void onBtnClick() {
//        android.util.Log.d("czh","CLICK 事件发生了");
//    }

    @Override
    protected void onStart() {
        super.onStart();
        testReflection();
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    public void testReflection() {
        Student stu1 = new Student();//这一new 产生一个Student对象，一个Class对象。
        Class stuClass = stu1.getClass();//获取Class对象
        System.out.println(stuClass.getName());

        //第二种方式获取Class对象
        Class stuClass2 = Student.class;
        System.out.println(stuClass == stuClass2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个




        //第三种方式获取Class对象sout
        try {
            Class stuClass3 = Class.forName("com.reflection.test.Student");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            //2.获取所有公有构造方法
            System.out.println("**********************所有公有构造方法*********************************");
            Constructor[] conArray = stuClass3.getConstructors();
            for(Constructor c : conArray){
                System.out.println(c);
            }


            System.out.println("************获取所有公有的字段********************");
             Field[] fieldArray = stuClass3.getFields();
            for(Field f : fieldArray){
                System.out.println(f);
            }
            System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
            fieldArray = stuClass3.getDeclaredFields();
            for(Field f : fieldArray){
                System.out.println(f);
            }
            System.out.println("*************获取公有字段**并调用***********************************");
            Field f = stuClass3.getField("name");
            System.out.println(f);
            //获取一个对象
            Object obj = stuClass3.getConstructor().newInstance();//产生Student对象--》Student stu = new Student();
            //为字段设置值
            f.set(obj, "刘德华");//为Student对象中的name属性赋值--》stu.name = "刘德华"
            //验证
            Student stu = (Student)obj;
            System.out.println("验证姓名：" + stu.name);


            System.out.println("**************获取私有字段****并调用********************************");
            f = stuClass3.getDeclaredField("phoneNum");
            System.out.println(f);
            f.setAccessible(true);//暴力反射，解除私有限定
            f.set(obj, "18888889999");
            System.out.println("验证电话：" + stu);



            System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象


            Class stuClass5 = Class.forName("com.reflection.test.Student");
            //2.获取所有公有方法
            System.out.println("***************获取所有的”公有“方法*******************");
            stuClass5.getMethods();
            Method[] methodArray = stuClass5.getMethods();
            for(Method m : methodArray){
                System.out.println(m);
            }
            System.out.println("***************获取所有的方法，包括私有的*******************");
            methodArray = stuClass5.getDeclaredMethods();
            for(Method m : methodArray){
                System.out.println(m);
            }
            System.out.println("***************获取公有的show1()方法*******************");
            Method m = stuClass5.getMethod("show1", String.class);
            System.out.println(m);
            //实例化一个Student对象
            Object obj1 = stuClass5.getConstructor().newInstance();
            m.invoke(obj1, "刘德华");

            System.out.println("***************获取私有的show4()方法******************");
            m = stuClass5.getDeclaredMethod("show4", int.class);
            System.out.println(m);
            m.setAccessible(true);//解除私有限定
            Object result = m.invoke(obj1, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
            System.out.println("返回值：" + result);


        } catch (Exception e) {
            e.printStackTrace();

        }



    }

}

