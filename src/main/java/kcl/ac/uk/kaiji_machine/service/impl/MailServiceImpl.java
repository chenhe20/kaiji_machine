package kcl.ac.uk.kaiji_machine.service.impl;

import kcl.ac.uk.kaiji_machine.service.MailService;
import kcl.ac.uk.kaiji_machine.util.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Value("${mail-service.to}")
    private String to;
    private StringBuffer mailBody = new StringBuffer();

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    ExcgRateServiceImpl excgRateService;

    @Override
    public void send() throws Exception{

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(generateSubject());

        // generate content
        this.mailBody.append(generateBodyPrefix())
                .append(collectExcgRate())
                .append(generateBodySuffix());

        helper.setText(mailBody.toString(), true);
        mailSender.send(message);
    }

    public String generateSubject() {
        String now = new SimpleDateFormat("HHmmss").format(new Date());
        if (Integer.valueOf(now.substring(0,2)) > 12) {
            return "Good Evening, News Here";
        } else {
            return "Good Morning, New Here";
        }
    }

    public String generateBodyPrefix() {
        return "<!DOCTYPE html><html><body>";
    }

    public String collectExcgRate() {
        return excgRateService
                .analysisExcgRate(excgRateService.queryRecentExcgRate());
    }

    public String generateBodySuffix() {
        return "</body></html>";
    }
}
