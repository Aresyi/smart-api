package com.ydj.smart.startup;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**  
 *
 * @author : Ares.yi
 * @createTime : 2014-11-10 上午11:13:42 
 * @version : 1.0 
 * @description : 
 *
 */
public class TestFuture {
	
	private final ExecutorService pool = Executors.newFixedThreadPool(2);
	
	public int getData() throws Exception{
		
		int i = invoke1();
		int j = invoke2();
		
		return i+j;
	}
	
	public int getData2() throws Exception{
		
		Future<Integer> f1 = null;
		Future<Integer> f2 = null;
		
		f1 = pool.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				return invoke1();
			}
			
		});
		
		f2 = pool.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				return invoke2();
			}
			
		});
		
		
		int i = f1.get();
		int j = f2.get();
		
		
		pool.shutdown();
		
		
		return i+j;
	}

	public static void main(String[] args) throws Exception {
		TestFuture obj = new TestFuture();
		
		long start = System.currentTimeMillis();
		obj.getData2();
		long end = System.currentTimeMillis();
		
		System.out.println(end-start);
	}
	
	
	
	private static int invoke1() throws InterruptedException{
		Thread.sleep(3000);
		return 1;
	}
	
	
	private static int invoke2() throws InterruptedException{
		Thread.sleep(2000);
		return 2;
	}
	
}
