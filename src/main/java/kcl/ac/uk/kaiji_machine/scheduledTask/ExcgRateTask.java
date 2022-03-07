package kcl.ac.uk.kaiji_machine.scheduledTask;

import kcl.ac.uk.kaiji_machine.dao.ExcgRate;
import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.service.impl.ExcgRateServiceImpl;
import kcl.ac.uk.kaiji_machine.util.PropertiesUtils;
import kcl.ac.uk.kaiji_machine.util.SpringContextUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 *
 * gather foreign exchange currency rate information
 */
public class ExcgRateTask extends AbstractTask {

    private ExcgRateServiceImpl excgRateService;

    private final String URL = PropertiesUtils.getProperty("crawler.excg-rate.url");
    private final String selector = PropertiesUtils.getProperty("crawler.excg-rate.selector");

    public ExcgRateTask(String name, String cron) {
        super(name, cron);
    }

    public ExcgRateTask(Task task) {
        super(task);
    }

    @Override
    public void doTask() throws Exception{
        Document document = Jsoup.connect(URL).get();
        Elements gbpElements = document.select(selector);

        String currencyName = gbpElements.select("> td:nth-child(1)").html();
        Double buyingRate = Double.valueOf(gbpElements.select("> td:nth-child(2)").html());
        Double cashBuyingRate = Double.valueOf(gbpElements.select("> td:nth-child(3)").html());
        Double sellingRate = Double.valueOf(gbpElements.select("> td:nth-child(4)").html());
        Double cashSellingRate = Double.valueOf(gbpElements.select("> td:nth-child(5)").html());

        String cnPubTimeOrigin = gbpElements.select("> td:nth-child(7)").html();
        cnPubTimeOrigin = cnPubTimeOrigin.substring(0, cnPubTimeOrigin.length() - 6);
        //Date cnPubTime = DateUtil.formatToDate(cnPubTimeOrigin, DateUtil.INSERT_FORMAT);

        Date date = new Date();

        ExcgRate excgRate = new ExcgRate().setCurrencyName(currencyName).setBuyingRate(buyingRate)
                .setCashBuyingRate(cashBuyingRate).setCashSellingRate(cashSellingRate)
                .setSellingRate(sellingRate).setCreatedTime(date);

        excgRateService = SpringContextUtils.getApplicationContext().getBean(ExcgRateServiceImpl.class);

        try {
            excgRateService.addExcgRate(excgRate);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
