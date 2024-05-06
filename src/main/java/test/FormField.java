package test;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jialu.yxl
 * @date 12/04/2023 19:42
 */
@Data
public class FormField implements Serializable {

    private static final long serialVersionUID = 2259593077236581777L;

    String key;

    /**
     * 标题
     */
    String label;

    /**
     * 属性
     */
    String field;

}
