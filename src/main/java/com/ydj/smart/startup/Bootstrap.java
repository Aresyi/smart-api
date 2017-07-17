package com.ydj.smart.startup;

import java.io.PrintStream;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
*
* @author : Ares.yi
* @createTime : 2014-11-10 上午11:13:42 
* @version : 1.0 
* @description : FIXME : 暂未完善
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
