package kcl.ac.uk.kaiji_machine.service.impl;

import kcl.ac.uk.kaiji_machine.dao.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 * 管理springboot定时任务
 */

@Component
public class ScheduledTaskServiceImpl {

    private final String TASK_CLASS_PREFIX = "kcl.ac.uk.kaiji_machine.scheduledTask.";
    private final String FIELD_NAME = "name";
    private final String FIELD_CRON = "cron";

    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private static Map<String, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();

    public void initAllTask() throws Exception{
        List<Task> tasks = taskService.queryAllTask();
        if (null == tasks) return;

        for (Task task:
             tasks) {
            if (!task.getStopStatus()) {
                scheduleTask(task);
            }
        }
    }

    public void scheduleTask(Task task) throws Exception{
        String taskName = task.getName();
        String cron = task.getCron();

        Class<?> clazz = Class.forName(TASK_CLASS_PREFIX + taskName);
        Constructor<?> constructor = clazz.getDeclaredConstructor(Task.class);
        Object oTask = constructor.newInstance(task);

            ScheduledFuture<?> future = threadPoolTaskScheduler
                    .schedule((Runnable) oTask, new CronTrigger(cron));
            tasks.put(taskName, future);

            System.out.println("Task" +taskName + " with cron: " + cron + "has been scheduled");


    }

    public void deleteTask(Task task) {
        String taskName = task.getName();
        tasks.get(taskName).cancel(true);
        tasks.remove(taskName);
    }


}
