package com.oj.onlinejudge;

import org.junit.jupiter.api.Test;
import org.python.util.PythonInterpreter;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Scanner;

@SpringBootTest
class OnlinejudgeApplicationTests {
    @Test
    void contextLoads() {

        File file = new File("C:/Users/luo'xing'yue/Desktop/out.txt");
        char[] c = new char[100];
        int count = 0;
        String ans = "";
        try {
            FileReader fr = new FileReader(file);
            while ((count = fr.read(c)) != -1){
                //将字符数组转换成字符串
                String s = new String(c,0,count);
                ans += s;
            }
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ans);
//        PythonInterpreter interpreter = new PythonInterpreter();
//
//        try {
//            FileInputStream fis = new FileInputStream("C:/Users/luo'xing'yue/Desktop/test.py");
//            {
//                //将标准输入重定向到fis输入流
//                System.setIn(fis);
//                //使用System.in创建Scanner对象，用于获取标准输入
//                Scanner sc = new Scanner(System.in);
//                //增加下面一行把回车作为分隔符
//                sc.useDelimiter("\n");
//                //判断是否还有下一个输入项
//                while(sc.hasNext())
//                {
//                    //输出输入项
//                    System.out.println("键盘输入的内容时："+sc.next());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        interpreter.execfile("D:/test.py");
    }

}
