package kcl.ac.uk.kaiji_machine.controller;

import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.facade.TaskServiceFacadeImpl;
import kcl.ac.uk.kaiji_machine.response.BaseResult;
import kcl.ac.uk.kaiji_machine.response.HandleResult;
import kcl.ac.uk.kaiji_machine.util.PropertiesUtils;
import kcl.ac.uk.kaiji_machine.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
@Controller
@RequestMapping("/task-mgt")
public class TaskController {

    @Autowired
    TaskServiceFacadeImpl taskServiceFacade;

    @RequestMapping(value = "/update-cron", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult updateTaskCron(@RequestBody TaskVO taskVO) throws Exception {

       taskServiceFacade.updateTaskCron(taskVO);

        return new BaseResult().setCode("100").setDesc("success");
    }

    @RequestMapping(value = "/shutdown", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult stopTask(@RequestBody TaskVO taskVO) throws Exception {

       taskServiceFacade.stopTask(taskVO);

        return new BaseResult().setCode("100").setDesc("success");
    }

    @RequestMapping(value = "/boot", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult startTask(@RequestBody TaskVO taskVO) throws Exception {

        taskServiceFacade.startTask(taskVO);

        return new BaseResult().setCode("100").setDesc("success");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public HandleResult<List<Task>> queryAllTask() {

        List<Task> tasks = taskServiceFacade.queryAllTask();

        return new HandleResult<List<Task>>().setCode("100").setDesc("success").setData(tasks);
    }

}
