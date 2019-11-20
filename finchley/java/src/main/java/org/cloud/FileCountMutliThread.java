package org.cloud;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangcai_zhu
 * @Description TODO
 * @Date 2019/10/25 14:47
 */
public class FileCountMutliThread {

    public static AtomicInteger file_count = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

//        File file = new File("F:\\");
        new FileTask(new File("F:\\"), threadPool);
//        new FileTask(new File("D:\\"), threadPool);
//        new FileTask(new File("E:\\"), threadPool);
//        new FileTask(new File("C:\\"), threadPool);

        while (true) {
            int last=file_count.get();
//            System.out.println("==================end====================" + last);
            Thread.sleep(1000);
//
            if(last==file_count.get()){
                threadPool.shutdown();
                if(threadPool.isShutdown()){
                    System.out.println(FileTask.EXT_MAP);
                    System.out.println("==================end====================" + file_count.get());
//                    FileTask.EXT_MAP.forEach();
                    break;
                }
            }

        }


    }

}
