package kcl.ac.uk.kaiji_machine.scheduledTask;


import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

public class TestTask extends abstractTask{

    @Autowired
    TaskServiceImpl taskService;

    private final String TASK_NAME = "TestTask";

    TestTask() {
        System.out.println("task created");
    }
    //private String cron;

//    public TestTask() {
//        setCron();
//    }

//    private void setCron() {
//        Task task = taskService.queryTaskByName(TASK_NAME);
//        this.cron = task.getCron();
//    }
//
//    private String getCron() {
//        return this.cron;
//    }

    @Override
    public void run() {
        System.out.println("任务执行时间：" + LocalDateTime.now());
    }
}
