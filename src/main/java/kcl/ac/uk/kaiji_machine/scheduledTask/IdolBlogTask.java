package kcl.ac.uk.kaiji_machine.scheduledTask;

import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.util.PropertiesUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
public class IdolBlogTask extends AbstractTask {

    public IdolBlogTask(String name, String cron) {
        super(name, cron);
    }

    public IdolBlogTask(Task task) {
        super(task);
    }

    private final String NogizakaURL = PropertiesUtils.getProperty("");

    private final String SakurazakaURL= PropertiesUtils.getProperty("crawler.blog.sakurazaka.url");
    private final String sakurazakaSelector = PropertiesUtils.getProperty("crawler.blog.sakurazaka.selector");

    @Override
    void doTask() throws Exception {
        // Get all blogs uploaded after the last task

        // Blogs of Sakurazaka46
        Document document = Jsoup.connect(SakurazakaURL).get();
        Elements sakurazakaEle = document.select(sakurazakaSelector);
        System.out.println(document);

    }
}
