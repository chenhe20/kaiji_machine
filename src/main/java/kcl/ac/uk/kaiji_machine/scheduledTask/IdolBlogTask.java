package kcl.ac.uk.kaiji_machine.scheduledTask;

import com.alibaba.fastjson.JSON;
import kcl.ac.uk.kaiji_machine.dao.IdolBlog;
import kcl.ac.uk.kaiji_machine.dao.Task;
import kcl.ac.uk.kaiji_machine.pojo.NogiApiRes;
import kcl.ac.uk.kaiji_machine.pojo.NogiBlog;
import kcl.ac.uk.kaiji_machine.service.impl.IdolBlogServiceImpl;
import kcl.ac.uk.kaiji_machine.service.impl.MailServiceImpl;
import kcl.ac.uk.kaiji_machine.util.DateUtils;
import kcl.ac.uk.kaiji_machine.util.PropertiesUtils;
import kcl.ac.uk.kaiji_machine.util.SpringContextUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

    public IdolBlogTask() {}

    private final String NogizakaURL = PropertiesUtils.getProperty("crawler.blog.nogizaka.url");
    private final String SakurazakaURL= PropertiesUtils.getProperty("crawler.blog.sakurazaka.url");
    private IdolBlogServiceImpl idolBlogService;

    @Override
    void doTask() throws Exception {
        // Service bean injection
        idolBlogService = SpringContextUtils.getBean(IdolBlogServiceImpl.class);

        // Updates sakurazaka46 and Nogizaka46 blogs
        updateBlogStorageByGroup("nogizaka46");
        updateBlogStorageByGroup("sakurazaka46");
    }

    public void updateBlogStorageByGroup(String group) throws JSONException, IOException, ParseException {

        // Gets newest blogs that not in the database
        //TODO: modify to adapt more groups
        List<IdolBlog> fetchedBlogs =
                group.equals("nogizaka46") ? fetchNogizakaBlogByPage(0) : fetchSakurazakaBlogByPage(0);
        // Fetches the last stored nogizaka blog
        IdolBlog latestBlog = idolBlogService.queryLatestBlogByGroup(group);
        // Iterates and inserts blogs until a blog already exists in the storage is found
        Date latestDate = latestBlog.getPostTime();
        for (int i = 0; i < fetchedBlogs.size(); i++) {
            IdolBlog blog = fetchedBlogs.get(i);
            if (blog.getPostTime().compareTo(latestDate) > 0) {
                idolBlogService.insertBlog(blog);
            } else if (blog.getPostTime().compareTo(latestDate) == 0) {
                // compares the content to distinguish
                if (!blog.getBgImageURL().equals(latestBlog.getBgImageURL())) {
                    idolBlogService.insertBlog(blog);
                } else break;
            } else break; // Breaks when date is earlier than the latest (warn: will never reach)
        }
    }

    public List<IdolBlog> fetchSakurazakaBlogByPage(Integer pageNumber) throws IOException, ParseException {

        String pageUrl = new String(SakurazakaURL);
        pageUrl = pageUrl.substring(0, pageUrl.length() - 1).concat(String.valueOf(pageNumber));

        Document document = Jsoup.connect(pageUrl).get();
        Elements liElements = document.select("ul.com-blog-part li.box");
        LinkedList<IdolBlog> blogs = new LinkedList<>();

        for (Element element : liElements) {
            LinkedList<String> list = new LinkedList<>();
            String link = "https://sakurazaka46.com" + element.select("a").attr("href");

            String name = element.select(".name").text();

            String dateOfString = element.select(".date.wf-a").text();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date date = format.parse(dateOfString);


            String title = element.select(".title").text();
            String lead = element.select(".lead").text();

            Element spanElement = element.select("span.img").first();
            String style = spanElement.attr("style");
            int start = style.indexOf("url(") + 4;
            int end = style.indexOf(")", start);
            String photoLink = style.substring(start, end);
            if (photoLink.substring(0, 7).equals("/images"))
                photoLink = "https://sakurazaka46.com" + photoLink;

            IdolBlog blog = new IdolBlog();
            blog.setMemberName(name)
                    .setTitle(title)
                    .setExcerpt(lead)
                    .setPostURL(link)
                    .setBgImageURL(photoLink)
                    .setPostTime(date)
                    .setGroup("sakurazaka46");
            blogs.add(blog);
        }

        return blogs;
    }

    public List<IdolBlog> fetchNogizakaBlogByPage(Integer pageNumber) throws JSONException {

        Integer calPageNumber = pageNumber * 32;
        String pageUrl = new String(NogizakaURL);
        pageUrl = pageUrl.substring(0, pageUrl.length() - 1).concat(String.valueOf(calPageNumber));

        RestTemplate restTemplate = SpringContextUtils.getApplicationContext().getBean(RestTemplate.class);
        ResponseEntity<String> response = restTemplate.exchange(pageUrl, HttpMethod.GET, null, String.class);
        String rawString = response.getBody();

        // unicode decode
        String data = StringEscapeUtils.unescapeJava(rawString);
        // String trim
        data = data.substring(4, data.length()-2);
        // Removes HTML tags
        data = data.replaceAll("<(\\S*?)[^>]*>.*?|<.*? />", "");
        // Removes double quotes inside attributes value
        data = data.replaceAll("(?<![,{}:])\"(?![,{}:])", "");
        // String to JSON
        JSONObject jsonObject = new JSONObject(data);
        // JSON to Object
        NogiApiRes nogiApiRes = JSON.parseObject(jsonObject.toString(), NogiApiRes.class);
        List<NogiBlog> nogiBlogs = nogiApiRes.getData();

        LinkedList<IdolBlog> blogs = new LinkedList<>();

        nogiBlogs.forEach(blog -> {
            IdolBlog idolBlog = new IdolBlog();
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date date = null;
            try {
                date = format.parse(blog.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            idolBlog.setGroup("nogizaka46")
                    .setTitle(blog.getTitle())
                    .setExcerpt(blog.getText())
                    .setPostURL(blog.getLink())
                    .setBgImageURL(blog.getImg())
                    .setMemberName(blog.getName())
                    .setPostTime(date);
            blogs.add(idolBlog);
        });

        return blogs;
    }


}
