/**
 * FileName: MonitoringCenterService
 * Author:   jack.xue
 * Date:     2019/1/3 14:23
 * Description: 监控中心服务
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/1/3           1.0.0              描述
 */
package cn.bw.fitzcloud.fitzcloudserver.service;

import cn.bw.fitzcloud.fitzcloudserver.bean.MailBean;
import cn.bw.fitzcloud.fitzcloudserver.bean.ServiceInfo;
import cn.bw.fitzcloud.fitzcloudserver.config.MonitoringCenterProperties;
import cn.bw.fitzcloud.fitzcloudserver.entity.MonitoringCenter;
import cn.bw.fitzcloud.fitzcloudserver.mail.MailHandler;
import cn.bw.fitzcloud.fitzcloudserver.repository.MonitoringCenterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 〈TODO〉<br>
 *
 * @author jack.xue
 * @create 2019/1/3
 * @since 1.0.0
 */
@Service
@Slf4j
public class MonitoringCenterService {

    @Autowired
    private MonitoringCenterProperties monitoringCenterProperties;

    @Autowired
    private MailHandler mailHandler;

    @Autowired
    private MonitoringCenterRepository monitoringCenterRepository;


    /**
     * 通知信息
     *
     * @param serviceInfo
     */
    public void handlerNotify(ServiceInfo serviceInfo) {
        if (Objects.isNull(serviceInfo)) {
            log.warn("serviceInfo is null");
            return;
        }
        if (Objects.isNull(serviceInfo.getServerStatus())) {
            log.warn("serviceInfo.getServerStatus() is null");
            return;
        }

        switch (serviceInfo.getServerStatus()) {
            case ON:
                log.debug("服务上线了, {}", serviceInfo.toString());
                if (monitoringCenterProperties.isOnlineNotify()) {
                    if (monitoringCenterProperties.isNotifyTypeMail()) {
                        MailBean mb = new MailBean();
                        mb.setSubject(monitoringCenterProperties.getMailTitle() + "-[" + serviceInfo.getAppName() + "]上线");
                        mb.setContent(">>>>>>> 服务上线,服务名:" + serviceInfo.getAppName() + ",端口号:" + serviceInfo.getPort());
                        mb.addRecipient(monitoringCenterProperties.getRecipients());
                        mailHandler.sendSimpleMail(mb);
                        log.info("邮件通知成功, {}", serviceInfo.toString());
                    }
                    if (monitoringCenterProperties.isNotifyTypeDb()) {
                        MonitoringCenter monitoringCenter = new MonitoringCenter();
                        monitoringCenter.setAppname(serviceInfo.getAppName());
                        monitoringCenter.setServer_id(serviceInfo.getServerId());
                        monitoringCenter.setServer_status(ServiceInfo.ServerStatus.ON);
                        monitoringCenterRepository.save(monitoringCenter);
                        log.info("DB存储成功, {}", monitoringCenter.toString());
                    }
                }
                break;
            case OFF:
                log.debug("服务下线了, {}", serviceInfo.toString());
                if (monitoringCenterProperties.isOfflineNotify()) {
                    if (monitoringCenterProperties.isNotifyTypeMail()) {
                        MailBean mb = new MailBean();
                        mb.setSubject(monitoringCenterProperties.getMailTitle() + "-[" + serviceInfo.getAppName() + "]下线");
                        mb.setContent(">>>>>>> 服务下线,服务名:" + serviceInfo.getAppName() + ",失效服务: " + serviceInfo.getServerId() + ",已被剔除!");
                        mb.addRecipient(monitoringCenterProperties.getRecipients());
                        mailHandler.sendSimpleMail(mb);
                        log.info("邮件通知成功, {}", serviceInfo.toString());
                    }
                    if (monitoringCenterProperties.isNotifyTypeDb()) {
                        MonitoringCenter monitoringCenter = new MonitoringCenter();
                        monitoringCenter.setAppname(serviceInfo.getAppName());
                        monitoringCenter.setServer_id(serviceInfo.getServerId());
                        monitoringCenter.setServer_status(ServiceInfo.ServerStatus.OFF);
                        monitoringCenterRepository.save(monitoringCenter);
                        log.info("DB存储成功, {}", monitoringCenter.toString());
                    }
                }
                break;
            default:
                log.warn("未知状态, {}", serviceInfo.toString());
        }
    }


}