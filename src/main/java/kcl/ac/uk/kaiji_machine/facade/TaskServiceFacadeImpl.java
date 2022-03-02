package kcl.ac.uk.kaiji_machine.facade;

import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.scheduledTask.ScheduledTaskService;
import kcl.ac.uk.kaiji_machine.service.impl.TaskServiceImpl;
import kcl.ac.uk.kaiji_machine.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    ScheduledTaskService scheduledTaskService;

    public void stopTask(TaskVO taskVO) throws Exception{
        //TODO: 1.validate task exists 2.validate the stop status

        // query task
        Task task = taskService.queryTaskByName(taskVO.getName());

        // update task
        task.setStopStatus(true);
        taskService.updateTask(task);

        // update scheduled task
        scheduledTaskService.deleteTask(task);
    }

    public void startTask(TaskVO taskVO) throws Exception{
        //TODO: 1.validate task exists 2.validate the stop status

        // query task
        Task task = taskService.queryTaskByName(taskVO.getName());

        // update task
        task.setStopStatus(false);
        taskService.updateTask(task);

        // update scheduled task
        scheduledTaskService.scheduleTask(task);
    }

    public void updateTaskCron(TaskVO taskVO) throws Exception {
        //TODO: validate task exists

        // query task
        Task task = taskService.queryTaskByName(taskVO.getName());

        // update task
        task.setCron(taskVO.getCron());
        taskService.updateTask(task);

        // update scheduled task
        scheduledTaskService.deleteTask(task);
        scheduledTaskService.scheduleTask(task);
    }
}
