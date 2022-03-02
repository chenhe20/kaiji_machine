package kcl.ac.uk.kaiji_machine.controller;

import kcl.ac.uk.kaiji_machine.facade.TaskServiceFacadeImpl;
import kcl.ac.uk.kaiji_machine.response.BaseResult;
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
    TaskServiceFacadeImpl taskServiceFacade;

    @RequestMapping(value = "/update-cron", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult updateTaskCron(TaskVO taskVO) throws Exception {

       taskServiceFacade.updateTaskCron(taskVO);

        return new BaseResult().setCode("100").setDesc("success");
    }

    @RequestMapping(value = "/shutdown", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult stopTask(TaskVO taskVO) throws Exception {

       taskServiceFacade.stopTask(taskVO);

        return new BaseResult().setCode("100").setDesc("success");
    }

    @RequestMapping(value = "/boot", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult startTask(TaskVO taskVO) throws Exception {

        taskServiceFacade.startTask(taskVO);

        return new BaseResult().setCode("100").setDesc("success");
    }
}
