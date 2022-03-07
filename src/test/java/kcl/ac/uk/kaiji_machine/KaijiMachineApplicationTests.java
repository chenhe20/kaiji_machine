package kcl.ac.uk.kaiji_machine;

import kcl.ac.uk.kaiji_machine.dao.ExcgRate;
import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.dao.TaskExample;
import kcl.ac.uk.kaiji_machine.mapper.TaskMapper;
import kcl.ac.uk.kaiji_machine.service.impl.ExcgRateServiceImpl;
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

    @Autowired
    ExcgRateServiceImpl excgRateService;

    @Test
    void contextLoads() {
        excgRateService.addExcgRate(new ExcgRate().setBuyingRate(8.88));
    }

}
