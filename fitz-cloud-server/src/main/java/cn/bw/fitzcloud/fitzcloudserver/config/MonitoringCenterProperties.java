/**
 * FileName: MonitoringCenterProperties
 * Author:   jack.xue
 * Date:     2019/1/3 14:24
 * Description: 配置
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/1/3           1.0.0              描述
 */
package cn.bw.fitzcloud.fitzcloudserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 〈TODO〉<br>
 *
 * @author jack.xue
 * @create 2019/1/3
 * @since 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "monitoring.center")
@Data
public class MonitoringCenterProperties {

    private boolean onlineNotify = false;

    private boolean offlineNotify = false;

    private boolean notifyTypeMail = false;

    private String mailTitle;

    private String[] recipients;

    private boolean notifyTypeDb = false;
}