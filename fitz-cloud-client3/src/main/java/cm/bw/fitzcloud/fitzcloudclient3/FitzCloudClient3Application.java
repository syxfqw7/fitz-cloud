package cm.bw.fitzcloud.fitzcloudclient3;

import com.csf.EurekaClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication(scanBasePackages = {"com.csf"})
//@ComponentScan(basePackages = {"com.csf"})
@SpringBootApplication
@RestController
public class FitzCloudClient3Application {

    public static void main(String[] args) {
        //SpringApplication.run(new Object[]{FitzCloudClient3Application.class, EurekaClientConfig.class}, args);
        SpringApplication.run(FitzCloudClient3Application.class, args);
    }

    @Autowired(required = false)
    private EurekaClientConfig myconfig;


    @RequestMapping("/hello")
    public String hello() {

        if (null == myconfig) {
            return "hello-3-null";
        }

        return "helo-3" + myconfig.getAppname() + ":" + myconfig.getPort();
    }

}

