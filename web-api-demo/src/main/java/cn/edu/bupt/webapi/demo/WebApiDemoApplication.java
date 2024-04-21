package cn.edu.bupt.webapi.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.bupt.webapi.demo.mapper")
public class WebApiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApiDemoApplication.class, args);
    }

}
