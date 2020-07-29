package cn.bw.fiztcloud.fitzcloudclient2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class FitzCloudClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(FitzCloudClient2Application.class, args);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "helo-2";
    }

}

