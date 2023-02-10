package kcl.ac.uk.kaiji_machine.facade;

import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.service.impl.ScheduledTaskServiceImpl;
import kcl.ac.uk.kaiji_machine.service.impl.TaskServiceImpl;
import kcl.ac.uk.kaiji_machine.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Service("TaskServiceFacadeImpl")
public class TaskServiceFacadeImpl {

    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    ScheduledTaskServiceImpl scheduledTaskService;

    public void stopTask(TaskDto taskDto) throws Exception{
        //TODO: 1.validate task exists 2.validate the stop status

        // query task
        Task task = taskService.queryTaskByName(taskDto.getName());

        // update task
        task.setStopStatus(true);
        task.setModifiedDate(null);
        taskService.updateTask(task);

        // update scheduled task
        scheduledTaskService.deleteTask(task);
    }

    public void startTask(TaskDto taskDto) throws Exception{
        //TODO: 1.validate task exists 2.validate the stop status

        // query task
        Task task = taskService.queryTaskByName(taskDto.getName());

        // update task
        task.setStopStatus(false);
        task.setModifiedDate(null);
        taskService.updateTask(task);

        // update scheduled task
        scheduledTaskService.scheduleTask(task);
    }

    public void updateTaskCron(TaskDto taskDto) throws Exception {
        //TODO: validate task exists

        // query task
        Task task = taskService.queryTaskByName(taskDto.getName());

        // update task
        task.setCron(taskDto.getCron());
        taskService.updateTask(task);

        // update scheduled task if task is in service
        if (task.getStopStatus() != true) {
            scheduledTaskService.deleteTask(task);
            scheduledTaskService.scheduleTask(task);
        }
    }

    public List<Task> queryAllTask() {
        return taskService.queryAllTask();
    }
}
