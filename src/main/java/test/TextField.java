package test;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jialu.yxl
 * @date 13/04/2023 11:18
 */
@Data
public class TextField extends FormField implements Serializable {

    private static final long serialVersionUID = -2746131421544052667L;

    public TextField(String key, String label) {
        this.field = "TextField";
        this.label = label;
        this.key = key;
    }

    public TextField() {
    }

    /**
     * 占位提示
     */
    private String placeHolder;

    /**
     * 值
     */
    private String value;

    /**
     * 最大长度
     */
    private Integer maxLen;

    /**
     * 是否是纯数字
     */
    private Boolean isNumber;
}
