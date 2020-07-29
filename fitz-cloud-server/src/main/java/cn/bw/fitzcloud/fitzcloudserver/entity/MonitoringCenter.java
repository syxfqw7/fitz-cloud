/**
 * FileName: MonitoringCenter
 * Author:   jack.xue
 * Date:     2019/1/4 14:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/1/4           1.0.0              描述
 */
package cn.bw.fitzcloud.fitzcloudserver.entity;

import cn.bw.fitzcloud.fitzcloudserver.bean.ServiceInfo;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 〈TODO〉<br>
 *
 * @author jack.xue
 * @create 2019/1/4
 * @since 1.0.0
 */
@Entity
@Table(name = "test_monitoring_center")
@Data
public class MonitoringCenter {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    private String appname;

    private String server_id;

    @Enumerated(EnumType.STRING)
    //@Column(nullable = false, name = "SERVER_STATUS")
    private ServiceInfo.ServerStatus server_status;

    private Date create_date = new Date();


}