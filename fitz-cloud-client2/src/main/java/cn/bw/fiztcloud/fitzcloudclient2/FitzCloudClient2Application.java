package cn.bw.fiztcloud.fitzcloudclient2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FitzCloudClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(FitzCloudClient2Application.class, args);
    }

}

