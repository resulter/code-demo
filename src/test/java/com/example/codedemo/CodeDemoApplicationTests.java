package com.example.codedemo;

import com.example.codedemo.service.SerialNumberService;
import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeDemoApplicationTests {
    @Autowired
    SerialNumberService serialNumberService;

    @Test
    public void contextLoads() throws Exception {
        String aa = serialNumberService.generate("11");
        System.out.println(aa);
    }

    @Test
    public void ttt() throws  Exception{
        for (int i = 0; i < 15; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String generate = serialNumberService.generate("22");
                        System.out.println("-------->>> j:  "+generate);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    /*for (int j = 0; j < 50; j++) {
                        try {
                            System.out.println("-------->>> j:  "+serialNumberService.generate( "22"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }*/
                }
            }).start();
        }
    }


    @Test
    public void MultiRequestsTest() {
        // 构造一个Runner
        TestRunnable runner = new TestRunnable() {
            @Override
            public void runTest() throws Throwable {
                StringBuffer s= new StringBuffer();
                s.append("{");
                for (int i = 0; i < 30; i++) {
                    s.append(serialNumberService.generate("22"));
                    s.append(",");
                }
                s.append("}");
                System.out.println("result:   "+s);
                //你的测试内容
            }
        };
        int runnerCount = 30;
        //Rnner数组，想当于并发多少个。
        TestRunnable[] trs = new TestRunnable[runnerCount];
        for (int i = 0; i < runnerCount; i++) {
            trs[i] = runner;
        }
        // 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入
        MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);
        try {
            // 开发并发执行数组里定义的内容
            mttr.runTestRunnables();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
