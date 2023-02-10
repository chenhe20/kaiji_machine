package kcl.ac.uk.kaiji_machine.service;

import kcl.ac.uk.kaiji_machine.dao.IdolBlog;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
public interface IdolBlogService {

    void insertBlogs(List<IdolBlog> blogs);

    void insertBlog(IdolBlog blog);

    List<IdolBlog> queryRecentBlogByGroup(String group);

    IdolBlog queryLatestBlogByGroup(String group);

    void initSakurazakaBlogDB(Integer pageNumber) throws IOException, ParseException;

    void initNogizakaBlogDB(Integer pageNumber) throws IOException, ParseException, JSONException;
}
