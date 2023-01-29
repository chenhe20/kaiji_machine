package kcl.ac.uk.kaiji_machine.dao;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Accessors(chain = true)
@Data
@Document(collection = "idol_blog")
public class IdolBlog {

    @MongoId
    private Long ID;

    @Field("member_name")
    private String memberName;

    /**
     * background image url
     */
    @Field("bg_image_url")
    private String bgImageURL;

    private String title;

    @Field("post_time")
    private Date postTime;

    private Date createdTime;

    @Field("post_url")
    private String postURL;

    /**
     * excerpts from the post
     */
    private String excerpt;

    private String group;
}
