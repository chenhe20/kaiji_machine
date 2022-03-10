package kcl.ac.uk.kaiji_machine;

import kcl.ac.uk.kaiji_machine.dao.ExcgRate;
import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.dao.TaskExample;
import kcl.ac.uk.kaiji_machine.mapper.TaskMapper;
import kcl.ac.uk.kaiji_machine.service.impl.ExcgRateServiceImpl;
import kcl.ac.uk.kaiji_machine.service.impl.MailServiceImpl;
import kcl.ac.uk.kaiji_machine.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@SpringBootTest
class KaijiMachineApplicationTests {

    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    ExcgRateServiceImpl excgRateService;

    @Autowired
    MailServiceImpl mailService;


    @Autowired
    JavaMailSender mailSender;

    public void sendMail(String text2Add) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom("kaijimachine@gmail.com");
        helper.setTo("chnhechen@gmail.com");
        helper.setSubject("HTML generator testing ");

        StringBuffer text = new StringBuffer("<!DOCTYPE html><html><body>");
        text.append(text2Add);
        text.append("</body></html>");

        helper.setText(text.toString(),true);
        mailSender.send(message);
    }

    @Test
    void contextLoads() {
        try {
            mailService.send();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
