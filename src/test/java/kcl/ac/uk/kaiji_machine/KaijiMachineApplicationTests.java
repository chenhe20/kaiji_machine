package kcl.ac.uk.kaiji_machine;

import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.dao.TaskExample;
import kcl.ac.uk.kaiji_machine.mapper.TaskMapper;
import kcl.ac.uk.kaiji_machine.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class KaijiMachineApplicationTests {

    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    TaskMapper taskMapper;

    @Test
    void contextLoads() {

        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        //criteria.andNameEqualTo("cronTest");
        //taskMapper.selectByExample(taskExample).stream().forEach(System.out::println);
        String name = taskMapper.selectByPrimaryKey(1).getName();
        System.out.println(name);
    }

}
