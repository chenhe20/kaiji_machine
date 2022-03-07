package kcl.ac.uk.kaiji_machine.scheduledTask;


import kcl.ac.uk.kaiji_machine.dao.Task;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

public class TestTask extends AbstractTask {

    private final String TASK_NAME = "TestTask";
    //private String PY_SCRIPT_SUFFIX = "/test_task.py";
    //private String PY_SCRIPT_PATH = super.PY_SCRIPT_PREFIX + this.PY_SCRIPT_SUFFIX;

    public TestTask(Task task) {
        super(task);
        //this.PY_SCRIPT_SUFFIX = "/test_task.py";
    }

    public TestTask(String name, String cron) {
        super(name, cron);
        //this.PY_SCRIPT_SUFFIX = "/test_task.py";
    }


    @Override
    public void doTask() throws Exception{
        //execCommand();
    }

    private void execCommand() throws Exception{
        String COMMAND = this.generateCommand();
        System.out.println(COMMAND);

        Process p = Runtime.getRuntime().exec(COMMAND);
        BufferedReader  br = new BufferedReader(new
                InputStreamReader(p.getInputStream()));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
        p.waitFor();
    }


}
