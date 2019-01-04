/**
 * FileName: ServiceInfo
 * Author:   jack.xue
 * Date:     2019/1/3 14:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/1/3           1.0.0              描述
 */
package cn.bw.fitzcloud.fitzcloudserver.bean;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 〈TODO〉<br> 
 *
 * @author jack.xue
 * @create 2019/1/3
 * @since 1.0.0
 */
@Data
public class ServiceInfo {

    private String appName;

    private int port;

    private String serverId;

    private ServerStatus serverStatus = ServerStatus.NULL;

    public ServiceInfo(){};

    public ServiceInfo(String appName, int port, String serverId, ServerStatus serverStatus){
        this.appName = appName;
        this.port = port;
        this.serverId = serverId;
        this.serverStatus = serverStatus;
    }

    public static enum ServerStatus{
        ON, OFF, RENEW, NULL
    }
}

