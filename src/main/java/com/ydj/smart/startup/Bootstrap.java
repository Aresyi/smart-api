package com.ydj.smart.startup;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.PrintStream;

/**
*
* @author : Ares.yi
* @createTime : 2017-8-1 上午11:13:42
* @version : 1.0 
* @description : 这个类的作用与在web.xml中配置负责初始化Spring应用上下文的监听器作用类似，只不过在这里不需要编写额外的XML文件了。
*
 */
@EnableAutoConfiguration
@ComponentScan("com.ydj.smart.*")
@RestController("/")
public class Bootstrap extends WebMvcConfigurerAdapter {

    /**
     * Start spring-boot
     * 
     * @param args
     * @throws Exception
     *
     * @author : Ares.yi
     * @createTime : 2016年11月21日 下午2:16:14
     */
    public static void main(String[] args) throws Exception {
        System.setErr(new PrintStream(System.out) {
            @Override
            public void write(int b) {
                super.write(b);
            }

            @Override
            public void write(byte[] buf, int off, int len) {
                super.write(buf, off, len);
            }
        });
        new SpringApplicationBuilder(Bootstrap.class).run(args);
    }

}
