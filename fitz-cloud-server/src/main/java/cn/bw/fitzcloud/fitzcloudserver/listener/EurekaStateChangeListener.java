/**
 * FileName: EurekaStateChageListener
 * Author:   jack.xue
 * Date:     2018/12/29 16:46
 * Description: eurekaStateChangeListener 监听节点
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2018/12/29           1.0.0              描述
 */
package cn.bw.fitzcloud.fitzcloudserver.listener;

import cn.bw.fitzcloud.fitzcloudserver.bean.ServiceInfo;
import cn.bw.fitzcloud.fitzcloudserver.service.MonitoringCenterService;
import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 〈TODO〉<br>
 *
 * @author jack.xue
 * @create 2018/12/29
 * @since 1.0.0
 */
@Component
@Slf4j
public class EurekaStateChangeListener {

    @Autowired
    private MonitoringCenterService monitoringCenterService;

    @EventListener(condition = "#event.replication==false")
    public void listenRegisterd(EurekaInstanceRegisteredEvent event) {
        // 服务注册
        InstanceInfo instanceInfo = event.getInstanceInfo();
        String appName = instanceInfo.getAppName();
        Objects.requireNonNull(appName, "服务名不能为空!");
        log.info(">>>>>>> 服务上线,服务名:{},端口号:{}", appName, instanceInfo.getPort());
        ServiceInfo serviceInfo = new ServiceInfo(appName, instanceInfo.getPort(), instanceInfo.getInstanceId(), ServiceInfo.ServerStatus.ON);
        monitoringCenterService.handlerNotify(serviceInfo);
    }

    @EventListener
    public void listenCanceled(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
        // 服务断线事件
        String appName = eurekaInstanceCanceledEvent.getAppName();
        String serverId = eurekaInstanceCanceledEvent.getServerId();
        Objects.requireNonNull(appName, "服务名不能为空!");
        log.info(">>>>>>> 服务下线,服务名:{},失效服务:{},已被剔除!", appName, serverId);
        ServiceInfo serviceInfo = new ServiceInfo(appName, -1, serverId, ServiceInfo.ServerStatus.OFF);
        monitoringCenterService.handlerNotify(serviceInfo);
    }


    @EventListener(condition = "#event.replication==false")
    public void listenRenewed(EurekaInstanceRenewedEvent event) {
        // 服务续约
        log.debug(">>>>>>>>>>>>>>>Server续约:" + event.getServerId());
    }

    @EventListener
    public void listenAvailable(EurekaRegistryAvailableEvent event) {
        // 注册中心启动
        log.info(">>>>>>>>>>>>>>>Server注册中心:" + event);
    }

    @EventListener
    public void listenStarted(EurekaServerStartedEvent event) {
        // Server启动
        log.info(">>>>>>>>>>>>>>>Server启动:" + event);
    }

}