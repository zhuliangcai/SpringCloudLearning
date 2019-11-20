package org.cloud;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangcai_zhu
 * @Description TODO
 * @Date 2019/10/25 15:04
 */
public class FileTask implements Runnable {

    public static final Map<String, AtomicInteger> EXT_MAP = new ConcurrentHashMap();

    private File dir;
    private ExecutorService executor;

    public FileTask(File file, ExecutorService executor) {
        //不是文件夹不统计
        if(!file.isDirectory()){
            try {
                throw new RuntimeException(file.getCanonicalPath()+" is not directory");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.dir= file;
        this.executor = executor;
        executor.submit(this);
    }

    @Override
    public void run() {

        //统计文件夹下的文件列表
        File[] files = dir.listFiles();
        for (File f : files) {
            if(f.isFile()){
                FileCountMutliThread.file_count.incrementAndGet();
                String fName = f.getName();
                if(fName.lastIndexOf(".")>0) {
                    String ext = fName.substring(fName.lastIndexOf(".") + 1);
                    if(EXT_MAP.containsKey(ext)){
                        EXT_MAP.get(ext).incrementAndGet();
                    }else {
                        EXT_MAP.put(ext, new AtomicInteger(1));
                    }
                }else {
                    EXT_MAP.put("", new AtomicInteger(1));
                }
            }else {
                new FileTask(f,executor);
            }
        }
    }
}
