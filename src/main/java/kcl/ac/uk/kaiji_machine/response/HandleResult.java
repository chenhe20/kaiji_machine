package kcl.ac.uk.kaiji_machine.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
@Data
@Accessors(chain = true)
public class HandleResult<T> extends BaseResult{
    private String desc;
    private String code;
    T data;
}
