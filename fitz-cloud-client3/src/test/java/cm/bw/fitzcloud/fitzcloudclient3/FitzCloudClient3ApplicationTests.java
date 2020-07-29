package cm.bw.fitzcloud.fitzcloudclient3;

import com.csf.EurekaClientConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FitzCloudClient3Application.class, EurekaClientConfig.class})
public class FitzCloudClient3ApplicationTests {

    @Autowired(required = false)
    private EurekaClientConfig myconfig;


    @Test
    public void contextLoads() {
        System.out.println("myconfig: " + myconfig);
    }

    @Test
    public void contextLoads2() {
        System.out.println("myconfig: " + myconfig);
    }

}

