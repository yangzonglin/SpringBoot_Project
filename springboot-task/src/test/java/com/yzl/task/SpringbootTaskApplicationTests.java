package com.yzl.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringbootTaskApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSenderImpl;

    //测试普通邮件
    @Test
    void contextLoads() {
        //简单邮件对象
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件设置
        simpleMailMessage.setSubject("通知：12点吃饭");//邮件标题
        simpleMailMessage.setText("到12点吃饭啦");//邮件内容，默认非html格式内容
        simpleMailMessage.setTo("404230554@qq.com");//发送给哪些邮箱
        simpleMailMessage.setFrom("981768766@qq.com");//谁发送的

        javaMailSenderImpl.send(simpleMailMessage);
    }

    //发送复杂邮件，包含附件等
    @Test
    void sendMessage() throws Exception {
        //创建复杂邮件对象
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);//true是包含文件上传，即附件
        //使用helper对象进行邮件设置
        mimeMessageHelper.setSubject("通知：12点吃饭");//邮件标题
        mimeMessageHelper.setText("<b style='color:red'>到12点吃饭啦</b>",true);//邮件内容，默认非html格式内容
        mimeMessageHelper.setTo("3575377587@qq.com");//发送给哪些邮箱
        mimeMessageHelper.setFrom("981768766@qq.com");//谁发送的
        //上传文件
        mimeMessageHelper.addAttachment("1.jpg",new File("E:\\高清图片\\QQ图片20200205173622.jpg"));
        mimeMessageHelper.addAttachment("2.jpg",new File("E:\\高清图片\\QQ图片20200205173643.jpg"));

        javaMailSenderImpl.send(mimeMessage);
    }

}
