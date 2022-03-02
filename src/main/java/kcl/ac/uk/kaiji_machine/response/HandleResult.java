package kcl.ac.uk.kaiji_machine.response;

import lombok.Data;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
@Data
public class HandleResult<T> extends BaseResult{
    T data;
}
