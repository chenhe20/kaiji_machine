package kcl.ac.uk.kaiji_machine.service.impl;

import kcl.ac.uk.kaiji_machine.dao.ExcgRate;
import kcl.ac.uk.kaiji_machine.service.ExcgRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 * 操作汇率mongodb数据
 */

@Service
public class ExcgRateServiceImpl implements ExcgRateService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    public void addExcgRate(ExcgRate excgRate) {
        mongoTemplate.insert(excgRate);
    }

    public List<ExcgRate> queryRecentExcgRate() {
        Query query = new Query();
        query.limit(10);
        query.with(Sort.by(Sort.Order.desc("createdTime")));
        List<ExcgRate> excgRates = mongoTemplate.find(query, ExcgRate.class);
        ExcgRate excgRate = excgRates.get(0);

        // Redis cache
        // redisTemplate.opsForValue().set("excgRate", excgRate);

        return excgRates;
    }

    @Override
    public String analyseExcgRate(List<ExcgRate> excgRates) {

        StringBuffer tableEle = new StringBuffer("<div><H1>Exchange Rate</H1><table>" +
                "<tr>\n" +
                "<th>Date</th>\n" +
                " <th>Cash Selling Rate</th>\n" +
                " <th>Selling Rate</th>\n" +
                "</tr>");

        excgRates.forEach(rate -> {
            tableEle.append("<tr>");
            //String date = DateUtils.getDateString(rate.getCreatedTime(), DateUtils.INSERT_FORMAT);
            tableEle.append(generateTdEle(rate.getCreatedTime()));
            tableEle.append(generateTdEle(rate.getSellingRate()));
            tableEle.append(generateTdEle(rate.getCashSellingRate()));
            tableEle.append("</tr>");
        });

        tableEle.append("</table></div>");
        return tableEle.toString();

        }

        public <T> String generateTdEle(T attr) {
            if (!String.valueOf(attr).isEmpty()) {
                if (attr instanceof Date) {
                    String tmp = String.valueOf(attr);
                    return "<td>" + tmp.substring(0,tmp.length() - 9).trim() + "</td>";
                } else {
                    return "<td>" + String.valueOf(attr) + "</td>";
                }
            } else {
                return "<td>" + "&nbsp" + "</td>";
            }
        }

    }


