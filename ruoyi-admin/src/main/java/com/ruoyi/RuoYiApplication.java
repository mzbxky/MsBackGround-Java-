package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan(value = "com.ruoyi.dao")
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("　◢██████◣　　　　　　◢████◣\n" +
                "◢◤　　　　　　◥◣　　　　◢◤　　　　 ◥◣\n" +
                "◤　　　　　　　　◥◣　　◢◤　　　　 　　█\n" +
                "▎　　　◢█◣　　　◥◣◢◤　 　◢█　　　█\n" +
                "◣　　◢◤　◥◣　　　　　 　　◢◣◥◣　◢◤\n" +
                "◥██◤　　◢◤　　　　　　　　　　◥◣\n" +
                "　　　　　　 █　●　　　　　　　●　 █\n" +
                "　　　　　 　█　〃　　　▄　　　〃　 █\n" +
                "　　　　　　 ◥◣　　　╚╩╝　　 　◢◤\n" +
                "　　　　　　　 ◥█▅▃▃　▃▃▅█◤\n" +
                "　　　　　　　　　 ◢◤　　　◥◣\n" +
                "　　　　　　　　 　█　　　　　█\n" +
                "　　　　　　　 　◢◤▕　　　▎◥◣\n" +
                "　　　　　　　 ▕▃◣◢▅▅▅◣◢▃┃");
    }
}
