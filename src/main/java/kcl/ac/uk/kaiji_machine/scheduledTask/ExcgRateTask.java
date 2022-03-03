package kcl.ac.uk.kaiji_machine.scheduledTask;

import kcl.ac.uk.kaiji_machine.dao.Task;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 *
 * gather foreign exchange currency rate information
 */
public class ExcgRateTask extends AbstractTask {


    public ExcgRateTask(String name, String cron) {
        super(name, cron);
    }

    public ExcgRateTask(Task task) {
        super(task);
    }

    @Override
    public void doTask() {

    }
}
