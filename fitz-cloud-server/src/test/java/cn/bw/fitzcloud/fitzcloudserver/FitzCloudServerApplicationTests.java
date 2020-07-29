package cn.bw.fitzcloud.fitzcloudserver;

import cn.bw.fitzcloud.fitzcloudserver.bean.MailBean;
import cn.bw.fitzcloud.fitzcloudserver.bean.ServiceInfo;
import cn.bw.fitzcloud.fitzcloudserver.config.MonitoringCenterProperties;
import cn.bw.fitzcloud.fitzcloudserver.entity.MonitoringCenter;
import cn.bw.fitzcloud.fitzcloudserver.entity.TCode;
import cn.bw.fitzcloud.fitzcloudserver.mail.MailHandler;
import cn.bw.fitzcloud.fitzcloudserver.repository.MonitoringCenterRepository;
import cn.bw.fitzcloud.fitzcloudserver.repository.TcodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FitzCloudServerApplicationTests {

    @Autowired
    private MailHandler mailHandler;

    @Autowired
    private MonitoringCenterProperties monitoringCenterProperties;

    @Autowired(required = false)
    private DataSource dataSource;

    @Autowired
    private TcodeRepository tcodeRepository;

    @Autowired
    private MonitoringCenterRepository monitoringCenterRepository;

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
    public void loadProperties() {
        log.info("pro: " + monitoringCenterProperties.toString());
        log.info(null != dataSource ? dataSource.toString() : "datasource is null");
    }

    @Test
    public void operDB() {
        //List<TCode> list = tcodeRepository.findAll();
        //list.forEach(System.out::println);
        long count = tcodeRepository.count();
        log.info("count: {}", count);
    }

    @Test
    public void operDB2() {
        MonitoringCenter monitoringCenter = new MonitoringCenter();
        monitoringCenter.setAppname("TEST2");
        monitoringCenter.setServer_id("xxxx");
        monitoringCenter.setCreate_date(new Date());
        monitoringCenter.setServer_status(ServiceInfo.ServerStatus.NULL);
        log.info("status: {}", monitoringCenter.getServer_status());
        monitoringCenterRepository.save(monitoringCenter);
        long count = monitoringCenterRepository.count();
        log.info("count: {}", count);
    }

}

