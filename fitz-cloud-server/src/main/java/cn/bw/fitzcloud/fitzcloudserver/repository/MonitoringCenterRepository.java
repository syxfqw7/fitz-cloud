/**
 * FileName: MonitoringCenterRepository
 * Author:   jack.xue
 * Date:     2019/1/4 14:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/1/4           1.0.0              描述
 */
package cn.bw.fitzcloud.fitzcloudserver.repository;

import cn.bw.fitzcloud.fitzcloudserver.entity.MonitoringCenter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 〈TODO〉<br>
 *
 * @author jack.xue
 * @create 2019/1/4
 * @since 1.0.0
 */
public interface MonitoringCenterRepository extends JpaRepository<MonitoringCenter, String> {

}