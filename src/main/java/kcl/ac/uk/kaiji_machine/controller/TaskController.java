package kcl.ac.uk.kaiji_machine.controller;

import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.response.BaseResult;
import kcl.ac.uk.kaiji_machine.scheduledTask.ScheduledTaskService;
import kcl.ac.uk.kaiji_machine.service.impl.TaskServiceImpl;
import kcl.ac.uk.kaiji_machine.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
@Controller
@RequestMapping("/task-mgt")
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    ScheduledTaskService scheduledTaskService;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult updateTask(TaskVO taskVO) throws Exception {

        // query task
        Task task = taskService.queryTaskByName(taskVO.getName());

        // update task
        task.setCron(taskVO.getCron());
        taskService.updateTask(task);

        // update scheduled task
        scheduledTaskService.deleteTask(task);
        scheduledTaskService.scheduleTask(task);

        return new BaseResult().setCode("100").setDesc("success");
    }

    @RequestMapping(value = "/shutdown", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult stopTask(TaskVO taskVO) throws Exception {

        // query task
        Task task = taskService.queryTaskByName(taskVO.getName());

        // update task
        task.setStopStatus(true);
        taskService.updateTask(task);

        // update scheduled task
        scheduledTaskService.deleteTask(task);

        return new BaseResult().setCode("100").setDesc("success");
    }

}
