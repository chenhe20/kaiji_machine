package kcl.ac.uk.kaiji_machine.service;

import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.vo.TaskVO;

import java.util.List;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
public interface TaskService {

   void addTask(Task task);

   void deleteTask(Task task);

   void updateTask(Task task);

   Task queryTask(Task task);

   Task queryTaskByName(String name);

   List<Task> queryAllTask();
}
