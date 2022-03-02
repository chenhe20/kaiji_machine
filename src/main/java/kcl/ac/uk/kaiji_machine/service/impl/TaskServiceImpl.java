package kcl.ac.uk.kaiji_machine.service.impl;

import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.dao.TaskExample;
import kcl.ac.uk.kaiji_machine.mapper.TaskMapper;
import kcl.ac.uk.kaiji_machine.scheduledTask.ScheduledTaskService;
import kcl.ac.uk.kaiji_machine.service.TaskService;
import kcl.ac.uk.kaiji_machine.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Override
    public void addTask(Task task) {
        //mongoTemplate.save(task);
    }

    @Override
    public void deleteTask(Task task) {

    }

    @Override
    public void updateTask(Task task) {
        taskMapper.updateByPrimaryKeySelective(task);
    }

    @Override
    public Task queryTask(Task task) {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andNameEqualTo(task.getName());
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        if (tasks.size() == 1) {
            return tasks.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Task queryTaskByName(String name) {
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        if (tasks.size() == 1) {
            return tasks.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Task> queryAllTask() {
        List<Task> tasks = taskMapper.selectByExample(new TaskExample());
        return tasks;
    }
}
