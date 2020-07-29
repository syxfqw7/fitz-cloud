/**
 * FileName: TCode
 * Author:   jack.xue
 * Date:     2019/1/4 13:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/1/4           1.0.0              描述
 */
package cn.bw.fitzcloud.fitzcloudserver.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 〈TODO〉<br>
 *
 * @author jack.xue
 * @create 2019/1/4
 * @since 1.0.0
 */
@Entity
@Table(name = "test_code_comcode")
@Data
public class TCode {

    @Id
    private String code;

    private String comcode;

}