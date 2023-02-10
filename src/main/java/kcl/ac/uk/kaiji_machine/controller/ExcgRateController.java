package kcl.ac.uk.kaiji_machine.controller;

import kcl.ac.uk.kaiji_machine.dao.ExcgRate;
import kcl.ac.uk.kaiji_machine.response.HandleResult;
import kcl.ac.uk.kaiji_machine.service.impl.ExcgRateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
@Controller
@RequestMapping("/excgrate")
public class ExcgRateController {

    @Autowired
    ExcgRateServiceImpl excgRateService;

    @RequestMapping(value = "/recent", method = RequestMethod.GET)
    @ResponseBody
    public HandleResult<List<ExcgRate>> queryExcgRate() {

        List<ExcgRate> excgRates = excgRateService.queryRecentExcgRate();

        return new HandleResult<List<ExcgRate>>().setData(excgRates).setCode("100").setDesc("success");
    }

}
