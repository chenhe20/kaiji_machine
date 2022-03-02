package kcl.ac.uk.kaiji_machine.command;

import kcl.ac.uk.kaiji_machine.scheduledTask.ScheduledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
@Component
@Order(1)
public class InitLineRunner implements CommandLineRunner {

    @Autowired
    ScheduledTaskService scheduledTaskService;

    @Override
    public void run(String... args) throws Exception {
        scheduledTaskService.initAllTask();
    }
}
