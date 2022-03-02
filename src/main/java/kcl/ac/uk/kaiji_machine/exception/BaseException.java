package kcl.ac.uk.kaiji_machine.exception;

import kcl.ac.uk.kaiji_machine.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Data
@Accessors(chain = true)
public class BaseException extends RuntimeException {
    private String code;
    private String desc;

    public BaseException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getDesc());
        this.code = errorCodeEnum.getCode();
    }

}
