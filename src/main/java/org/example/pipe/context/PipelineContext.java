package org.example.pipe.context;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 定义管道数据的上下文(通用信息)
 */
@Getter
@Setter
public class PipelineContext {
    /**
     * 处理的开始时间
     */
    private LocalDateTime startTime;

    /**
     * 处理的结束时间
     */
    private LocalDateTime endTime;


    /**
     * 获取数据名称 ，一般是使用该上下文的类信息
     * @return
     */
    public String getName(){
        return this.getClass().getSimpleName();
    }


}
