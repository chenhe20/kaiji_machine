package kcl.ac.uk.kaiji_machine.controller;

import kcl.ac.uk.kaiji_machine.dao.ExcgRate;
import kcl.ac.uk.kaiji_machine.dao.IdolBlog;
import kcl.ac.uk.kaiji_machine.response.BaseResult;
import kcl.ac.uk.kaiji_machine.response.HandleResult;
import kcl.ac.uk.kaiji_machine.service.IdolBlogService;
import kcl.ac.uk.kaiji_machine.service.impl.IdolBlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Controller
@RequestMapping("/blog")
public class IdolBlogController {

    @Autowired
    IdolBlogServiceImpl idolBlogService;

    @RequestMapping(value = "/initSakurazaka/{pageNumber}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult initSakurazakaDB(@PathVariable Integer pageNumber) throws IOException, ParseException {

        for (int i = 0; i < pageNumber; i++) {
            idolBlogService.initSakurazakaBlogDB(i);
        }

        return new BaseResult().setCode("100").setDesc("success");
    }

    @RequestMapping(value = "/initNogizaka/{pageNumber}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult initNogizakaDB(@PathVariable Integer pageNumber) throws IOException, ParseException, JSONException {

        for (int i = 0; i < pageNumber; i++) {
            idolBlogService.initNogizakaBlogDB(i);
        }

        return new BaseResult().setCode("100").setDesc("success");
    }

    @RequestMapping(value = "/recent/{group}", method = RequestMethod.GET)
    @ResponseBody
    public HandleResult<List<IdolBlog>> queryRecentBlog(@PathVariable String group) throws IOException, ParseException {

        List<IdolBlog> idolBlogs = idolBlogService.queryRecentBlogByGroup(group);

        return new HandleResult<List<IdolBlog>>().setCode("100").setDesc("success").setData(idolBlogs);
    }


}
