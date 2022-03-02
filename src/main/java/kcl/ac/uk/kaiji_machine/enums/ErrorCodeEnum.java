package kcl.ac.uk.kaiji_machine.enums;

import lombok.Data;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

public enum ErrorCodeEnum {
    SUCCESS("000", "SUCCESS"),
    SYSTEM_ERROR("001", "SYSTEM ERROR");

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String code;
    private String desc;

    ErrorCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
