package kcl.ac.uk.kaiji_machine.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.util.Date;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
@Data
@Accessors(chain = true)
@Document(collection = "excg_rate")
public class ExcgRate {
    @MongoId
    String id;
    String currencyName;
    Double buyingRate;
    Double cashBuyingRate;
    Double sellingRate;
    Double cashSellingRate;
    Date cnPubTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date createdTime;
}
