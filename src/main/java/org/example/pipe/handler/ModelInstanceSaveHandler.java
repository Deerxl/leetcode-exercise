package org.example.pipe.handler;

import org.example.pipe.context.InstanceBuildContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *  管道- 处理器-
 *  作用：保存模型实例 到相关db表中
 */
@Component
public class ModelInstanceSaveHandler implements ContextHandler<InstanceBuildContext> {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean handle(InstanceBuildContext context) {
        LOGGER.info("--保存模型实例到相关db表中--");
        // 模拟
        return true;
    }
}
