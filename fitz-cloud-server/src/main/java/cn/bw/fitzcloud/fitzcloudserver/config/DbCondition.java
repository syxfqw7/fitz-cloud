/**
 * FileName: DbCondition
 * Author:   jack.xue
 * Date:     2019/1/4 9:51
 * Description: 数据库初始化condition
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/1/4           1.0.0              描述
 */
package cn.bw.fitzcloud.fitzcloudserver.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 〈TODO〉<br> 
 *
 * @author jack.xue
 * @create 2019/1/4
 * @since 1.0.0
 */
public class DbCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return conditionContext.getEnvironment().getProperty("monitoring.center.notify-type-db",Boolean.class, false);
    }
}