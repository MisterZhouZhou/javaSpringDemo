package com.didispace.web;

import com.didispace.async.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private Task task;

    @GetMapping(value = "/index")
    public String index() throws Exception {
        long start = System.currentTimeMillis();

        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();

        while (true){
            if (task1.isDone() && task2.isDone() && task3.isDone()){
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
        return "scuess";
    }
}
