package cn.bw.fitzcloud.fitzcloudserver; /**
 * FileName: cn.bw.fitzcloud.fitzcloudserver.SimpleTest
 * Author:   jack.xue
 * Date:     2019/1/3 15:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/1/3           1.0.0              描述
 */

import cn.bw.fitzcloud.fitzcloudserver.bean.ServiceInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 〈TODO〉<br>
 *
 * @author jack.xue
 * @create 2019/1/3
 * @since 1.0.0
 */
@Slf4j
public class SimpleTest {

    @Test
    public void test() {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setServerStatus(ServiceInfo.ServerStatus.OFF);
        System.out.println(serviceInfo.getServerStatus().name());
        switch (serviceInfo.getServerStatus()) {
            case ON:
                log.info("服务上线了");break;
            case OFF:
                log.info("服务下线了");
            default:
                log.warn("未知状态");
        }
    }
}