package cn.bw.fitzcloud.fitzcloudserver;

import cn.bw.fitzcloud.fitzcloudserver.bean.MailBean;
import cn.bw.fitzcloud.fitzcloudserver.config.MonitoringCenterProperties;
import cn.bw.fitzcloud.fitzcloudserver.mail.MailHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FitzCloudServerApplicationTests {

    @Autowired
    private MailHandler mailHandler;

    @Autowired
    private MonitoringCenterProperties monitoringCenterProperties;

    @Test
    public void contextLoads() {
        log.info("log info");
        MailBean mb = new MailBean();
        //mb.setRecipient("yongjiumibao@163.com");
        //mb.setRecipient("betty@chinascope.com");
        //mb.setRecipient("jack.xue@chinascope.com");
        //String [] receArr = new String[]{"jack.xue@chinascope.com","yongjiumibao@163.com"};
        //mb.setRecipient(receArr);
        mb.addRecipient("jack.xue@chinascope.com");
        mb.addRecipient("yongjiumibao@163.com");
        mb.setSubject("test");
        mb.setContent("hello, i am jack ,test sending email, new server start");
        mailHandler.sendSimpleMail(mb);
        log.info("email send over");
    }

    @Test
    public void loadProperties(){
        log.info("pro: "+monitoringCenterProperties.toString());
    }

}

