package org.example.pipe.handler;

import com.alibaba.fastjson.JSON;
import org.example.pipe.context.InstanceBuildContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * 管道 - 处理器
 * 作用：通用处理，数据处理后日志打印
 */
@Component
public class CommonTailHandler implements ContextHandler<InstanceBuildContext> {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean handle(InstanceBuildContext context) {
        // 设置处理结束时间
        context.setEndTime(LocalDateTime.now());
        LOGGER.info("管道执行完毕：context={}", JSON.toJSONString(context));
        return true;
    }
}