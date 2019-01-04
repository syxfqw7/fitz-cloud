/**
 * FileName: MailHandler
 * Author:   jack.xue
 * Date:     2019/1/3 10:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/1/3           1.0.0              描述
 */
package cn.bw.fitzcloud.fitzcloudserver.mail;

import cn.bw.fitzcloud.fitzcloudserver.bean.MailBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 〈TODO〉<br> 
 *
 * @author jack.xue
 * @create 2019/1/3
 * @since 1.0.0
 */
@Component
@Slf4j
public class MailHandler {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String MAIL_SENDER;

    public void sendSimpleMail(MailBean mailBean) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //邮件发送人
            simpleMailMessage.setFrom(MAIL_SENDER);
            //邮件接收人
            if(!mailBean.getRecipientList().isEmpty()){
                String[] arr = new String[mailBean.getRecipientList().size()];
                mailBean.getRecipientList().toArray(arr);
                simpleMailMessage.setTo(arr);
            }else{
                simpleMailMessage.setTo(mailBean.getRecipient());
            }
            //邮件主题
            simpleMailMessage.setSubject(mailBean.getSubject());
            //邮件内容
            simpleMailMessage.setText(mailBean.getContent());
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("邮件发送失败", e.getMessage());
        }
    }
}