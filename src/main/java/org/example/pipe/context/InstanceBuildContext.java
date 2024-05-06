package org.example.pipe.context;

import org.example.pipe.Constant;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 模型实例构建的上下文
 */
@Getter
@Setter
public class InstanceBuildContext extends PipelineContext{

    /**
     * 模型id
     */
    private Long modelId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 表单输入
     */
    private Map<String, Object> formInput;

    /**
     *保存模型实例完成后，记录下id
     */
    private Long instanceId;

    /**
     * 错误信息
     */
    private String errorMsg;

    public String getName(){
        return Constant.INSTANCE_BUILD_CONTEXT;
    }
}
