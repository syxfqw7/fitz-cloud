/**
 * FileName: MailBean
 * Author:   jack.xue
 * Date:     2019/1/3 10:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/1/3           1.0.0              描述
 */
package cn.bw.fitzcloud.fitzcloudserver.bean;

import com.google.common.collect.Lists;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * 〈TODO〉<br> 
 *
 * @author jack.xue
 * @create 2019/1/3
 * @since 1.0.0
 */
@Data
public class MailBean {

    @NotNull(message = "收件人地址不能为空")
    @Email
    private String recipient;   //邮件接收人
    private String subject; //邮件主题
    private String content; //邮件内容

    public MailBean(){
        this.subject = "监控平台";
    }


    private List<String> recipientList = Lists.newArrayList();

    public List<String> addRecipient(String recipient){
        recipientList.add(recipient);
        return this.getRecipientList();
    }

    public List<String> addRecipient(String[] recipients){
        if(!Objects.isNull(recipients)){
            for(String recipient : recipients){
                recipientList.add(recipient);
            }
        }
        return this.getRecipientList();
    }

}