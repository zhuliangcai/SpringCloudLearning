package org.cloud;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangcai_zhu
 * @Description TODO
 * @Date 2019/10/25 14:47
 */
public class FileCount {

    private static AtomicInteger file_count=new AtomicInteger();

    public static void main(String[] args) {

        File file = new File("F:\\git\\");

        countFile(file);

        System.out.println(file_count);

    }

    private static void countFile(File file) {
        //不是文件夹不统计
        if(!file.isDirectory()){
            try {
                throw new RuntimeException(file.getCanonicalPath()+" is not directory");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //统计文件夹下的文件列表
        File[] files = file.listFiles();
        for (File f : files) {
            if(f.isFile()){
                file_count.incrementAndGet();
            }else {
                countFile(f);
            }
        }
    }
}
