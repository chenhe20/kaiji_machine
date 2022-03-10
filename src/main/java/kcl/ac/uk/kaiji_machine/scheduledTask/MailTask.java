package kcl.ac.uk.kaiji_machine.scheduledTask;

import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.service.impl.MailServiceImpl;
import kcl.ac.uk.kaiji_machine.util.SpringContextUtils;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
public class MailTask extends AbstractTask{

    private MailServiceImpl mailService;

    public MailTask(String name, String cron) {
        super(name, cron);
    }

    public MailTask(Task task) {
        super(task);
    }

    @Override
    void doTask() throws Exception {
        mailService = SpringContextUtils.getApplicationContext().getBean(MailServiceImpl.class);
        mailService.send();
    }
}
