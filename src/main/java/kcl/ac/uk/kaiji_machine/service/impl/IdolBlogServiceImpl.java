package kcl.ac.uk.kaiji_machine.service.impl;

import kcl.ac.uk.kaiji_machine.dao.ExcgRate;
import kcl.ac.uk.kaiji_machine.dao.IdolBlog;
import kcl.ac.uk.kaiji_machine.scheduledTask.IdolBlogTask;
import kcl.ac.uk.kaiji_machine.service.IdolBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Service
public class IdolBlogServiceImpl implements IdolBlogService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void insertBlogs(List<IdolBlog> blogs) {

    }

    @Override
    public void insertBlog(IdolBlog blog) {
        mongoTemplate.insert(blog, "idol_blog");
    }

    @Override
    public List<IdolBlog> queryRecentBlogByGroup(String group) {
        Query query = new Query();
        query.limit(20);
        query.with(Sort.by(Sort.Order.desc("post_time")));
        query.addCriteria(Criteria.where("group").is(group));
        List<IdolBlog> idolBlogs = mongoTemplate.find(query, IdolBlog.class);
        return idolBlogs;
    }

    @Override
    public IdolBlog queryLatestBlogByGroup(String group) {
        Query query = new Query();
        query.addCriteria(Criteria.where("group").is(group));
        query.with(Sort.by(new Sort.Order(Sort.Direction.DESC, "post_time")
                , new Sort.Order(Sort.Direction.ASC, "_id")));
        query.limit(1);
        List<IdolBlog> idolBlogs = mongoTemplate.find(query, IdolBlog.class);
        return idolBlogs.get(0);
    }

    @Override
    public void initSakurazakaBlogDB(Integer i) throws IOException, ParseException {

            List<IdolBlog> idolBlogs = new IdolBlogTask().fetchSakurazakaBlogByPage(i);
            for (IdolBlog blog : idolBlogs) {
                mongoTemplate.insert(blog, "idol_blog");
            }

    }

    @Override
    public void initNogizakaBlogDB(Integer i) throws IOException, ParseException, JSONException {

        List<IdolBlog> idolBlogs = new IdolBlogTask().fetchNogizakaBlogByPage(i);
        for (IdolBlog blog : idolBlogs) {
            mongoTemplate.insert(blog, "idol_blog");
        }
    }
}
