package kcl.ac.uk.kaiji_machine.service.impl;

import kcl.ac.uk.kaiji_machine.dao.ExcgRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 * 操作汇率mongodb数据
 */

@Component
public class ExcgRateServiceImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public void addExcgRate(ExcgRate excgRate) {
        mongoTemplate.insert(excgRate);
    }

}
