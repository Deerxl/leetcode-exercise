package org.example.pipe.handler;

import com.alibaba.fastjson.JSON;
import org.example.pipe.context.InstanceBuildContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 管道 - 处理器
 * 作用：通用处理，数据处理前日志打印
 */
@Component
public class CommonHeadHandler implements ContextHandler<InstanceBuildContext> {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean handle(InstanceBuildContext context) {
        LOGGER.info("管道开始执行：context={}", JSON.toJSONString(context));
        // 设置开始时间
        context.setStartTime(LocalDateTime.now());
        return true;
    }
}
