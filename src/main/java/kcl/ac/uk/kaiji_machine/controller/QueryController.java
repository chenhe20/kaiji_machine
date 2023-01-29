package kcl.ac.uk.kaiji_machine.controller;

import kcl.ac.uk.kaiji_machine.dao.ExcgRate;
import kcl.ac.uk.kaiji_machine.service.impl.ExcgRateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
@Controller
@RequestMapping("/info-query")
public class QueryController {

    @Autowired
    ExcgRateServiceImpl excgRateService;

    @RequestMapping(value = "/excgRate", method = RequestMethod.GET)
    @ResponseBody
    public List<ExcgRate> queryExcgRate() {
        return excgRateService.queryRecentExcgRate();
    }

}
