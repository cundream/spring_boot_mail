package com.github.cundream.spring_boot_mail;

import com.github.cundream.spring_boot_mail.vo.MailTemplate;
import com.github.cundream.spring_boot_mail.vo.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMailApplicationTests {

    @Autowired
    JavaMailSender javaMailSender;

    @Test
    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage(); //构建一个邮件对象
        message.setSubject("这是一封测试邮件"); //设置邮件主题
        message.setFrom("2289359823@qq.com"); //设置邮件发送者
        message.setTo(new String[]{"572906901@qq.com","lic@sunmnet.com"}); //设置邮件接收者，可以有多个接收者
        message.setCc("mengdy@sunmnet.com"); //设置邮件抄送人，可以有多个抄送人
        message.setBcc("1791926069@qq.com"); //设置隐秘抄送人，可以有多个
        message.setSentDate(new Date()); //设置邮件发送日期
        message.setText("这是测试邮件的正文"); //设置邮件的正文
        javaMailSender.send(message); //发送邮件
    }



    @Test
    public void sendAttachFileMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("2289359823@qq.com"); //设置邮件发送者
        helper.setTo(new String[]{"572906901@qq.com","lic@sunmnet.com"}); //设置邮件接收者，可以有多个接收者
        helper.setCc("mengdy@sunmnet.com"); //设置邮件抄送人，可以有多个抄送人
        helper.setBcc("1791926069@qq.com"); //设置隐秘抄送人，可以有多个
        helper.setSentDate(new Date()); //设置邮件发送日期
        helper.setText("这是测试邮件的正文"); //设置邮件的正文
        helper.addAttachment("hltx.jpg",new File("E:\\image\\hltx.jpg"));//文件名字  文件
        javaMailSender.send(mimeMessage);
    }


    @Test
    public void sendImgResMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("2289359823@qq.com"); //设置邮件发送者
        helper.setTo(new String[]{"572906901@qq.com","lic@sunmnet.com"}); //设置邮件接收者，可以有多个接收者
        helper.setCc("mengdy@sunmnet.com"); //设置邮件抄送人，可以有多个抄送人
        helper.setBcc("1791926069@qq.com"); //设置隐秘抄送人，可以有多个
        helper.setSentDate(new Date()); //设置邮件发送日期
        helper.setText("<p>hello 大家好，这是一封测试邮件，这封邮件包含两种图片，分别如下</p><p>第一张图片：</p><img src='cid:hltx'/><p>第二张图片：</p><img src='cid:bz'/>",true);
        helper.addInline("hltx",new FileSystemResource(new File("E:\\image\\hltx.jpg")));
        helper.addInline("bz",new FileSystemResource(new File("E:\\image\\bz.jpg")));
        javaMailSender.send(mimeMessage);
    }



    @Test
    public void sendFreemarkerMail() throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("2289359823@qq.com"); //设置邮件发送者
        helper.setTo(new String[]{"572906901@qq.com","lic@sunmnet.com"}); //设置邮件接收者，可以有多个接收者
        helper.setCc("mengdy@sunmnet.com"); //设置邮件抄送人，可以有多个抄送人
        helper.setBcc("1791926069@qq.com"); //设置隐秘抄送人，可以有多个
        helper.setSentDate(new Date()); //设置邮件发送日期
        helper.setSentDate(new Date());
        //构建 Freemarker 的基本配置
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        // 配置模板位置
        ClassLoader loader = SpringBootMailApplication.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader, "templates");
        //加载模板
        Template template = configuration.getTemplate("mail.ftl");
        MailTemplate mailTemplate = new MailTemplate();
        mailTemplate.setDate("2019-07-18 15:00:00");
        List<User> userList = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            User user = new User();
            user.setName("姓名"+i);
            user.setAge(i);
            user.setSex(i%1 == 0?"男":"女");
            userList.add(user);
        }
        mailTemplate.setUserList(userList);

        StringWriter out = new StringWriter();
        //模板渲染，渲染的结果将被保存到 out 中 ，将out 中的 html 字符串发送即可
        template.process(mailTemplate, out);
        helper.setText(out.toString(),true);
        javaMailSender.send(mimeMessage);
    }

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void sendThymeleafMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("2289359823@qq.com"); //设置邮件发送者
        helper.setTo("lic@sunmnet.com"); //设置邮件接收者，可以有多个接收者
        //helper.setCc("mengdy@sunmnet.com"); //设置邮件抄送人，可以有多个抄送人
       // helper.setBcc("1791926069@qq.com"); //设置隐秘抄送人，可以有多个
        helper.setSentDate(new Date());
        List<User> userList = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            User user = new User();
            user.setName("姓名"+i);
            user.setAge(i);
            user.setSex(i%2 == 0?"男":"女");
            userList.add(user);
        }
        Context context = new Context();
        context.setVariable("date", "2019-07-18 15:00:00");
        context.setVariable("userList",userList);
        String process = templateEngine.process("mail.html", context);
        helper.setText(process,true);
        javaMailSender.send(mimeMessage);
    }

}
