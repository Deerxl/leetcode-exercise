package org.example.pipe.handler;

import org.example.pipe.context.InstanceBuildContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 管道 处理器 -
 * 作用：根据输入创建模型实例
 */
@Component
public class ModelInstanceCreatorHandler implements ContextHandler<InstanceBuildContext> {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean handle(InstanceBuildContext context) {
        LOGGER.info("--根据输入数据创建模式实例--");
        /**
         *  模拟创建流程
         */
        return true;
    }
}
