package kcl.ac.uk.kaiji_machine.scheduledTask;

import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Component
public class ScheduledTaskService {

    private final String TASK_CLASS_PREFIX = "kcl.ac.uk.kaiji_machine.scheduledTask.";

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

        try {
            Class<?> clazz = Class.forName(TASK_CLASS_PREFIX + taskName);
            Object oTask = clazz.newInstance();
            ScheduledFuture<?> future = threadPoolTaskScheduler
                    .schedule((Runnable) oTask, new CronTrigger(cron));
            tasks.put(taskName, future);

            System.out.println("new task created with cron: " + cron);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(Task task) {
        String taskName = task.getName();
        tasks.get(taskName).cancel(true);
        tasks.remove(taskName);
    }


}
