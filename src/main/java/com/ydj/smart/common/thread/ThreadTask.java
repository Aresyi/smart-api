/************************************************************************************
 * Create at 2017/1/19
 *
 * Author:song.ty
 *
 *************************************************************************************/

package com.ydj.smart.common.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 线程任务
 *
 * @author song.ty
 * @create 2017-01-19-16:00
 **/
public class ThreadTask {

    /**
     * 公用线程池
     */
    public static Executor commonThreadPool = Executors.newFixedThreadPool(100);


    private ThreadTask() {

    }

    private  static ThreadTask instance = null;
    public static ThreadTask getInstance(){
        if(instance == null){
            instance = new ThreadTask();
        }
        return instance;
    }



    /**
     * 指定线程池 执行任务
     * @param executor
     * @param runnable
     */
    public void run(Executor executor,Runnable runnable){
        try {
            executor.execute(runnable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
