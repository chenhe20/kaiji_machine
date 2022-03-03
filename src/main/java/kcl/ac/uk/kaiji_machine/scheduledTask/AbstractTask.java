package kcl.ac.uk.kaiji_machine.scheduledTask;

import kcl.ac.uk.kaiji_machine.util.propertiesUtil;
import kcl.ac.uk.kaiji_machine.dao.Task;

import java.time.LocalDateTime;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */


public abstract class AbstractTask implements Runnable{

    public String PY_FILE_PATH_PREFIX = propertiesUtil.getProperty("python-script.path");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    private String name;
    private String cron;

    public AbstractTask(String name, String cron) {
        this.name = name;
        this.cron = cron;
        System.out.println("task" + name + " created");
    }

    public AbstractTask(Task task) {
        this.name = task.getName();
        this.cron = task.getCron();
        System.out.println("task" + name + " created");
    }

    public abstract void doTask() throws Exception;

    @Override
    public void run() {
        try {
            doTask();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Task" + this.name + "being executed：" + LocalDateTime.now());
    }
}
