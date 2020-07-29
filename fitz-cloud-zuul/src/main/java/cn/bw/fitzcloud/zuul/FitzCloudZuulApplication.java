package cn.bw.fitzcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@RestController
public class FitzCloudZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitzCloudZuulApplication.class, args);
    }

}
