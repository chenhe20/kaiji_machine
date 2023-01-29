package kcl.ac.uk.kaiji_machine.service;

import kcl.ac.uk.kaiji_machine.dao.ExcgRate;

import java.util.List;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
public interface ExcgRateService {

    public void addExcgRate(ExcgRate excgRate);

    public List<ExcgRate> queryRecentExcgRate();

    public String analyseExcgRate(List<ExcgRate> excgRates);

}
