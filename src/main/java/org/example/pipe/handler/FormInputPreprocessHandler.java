package org.example.pipe.handler;

import org.example.pipe.context.InstanceBuildContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *  管道 处理器
 *  作用：表单输入数据预处理
 */
@Component
public class FormInputPreprocessHandler implements ContextHandler<InstanceBuildContext> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean handle(InstanceBuildContext context) {
        LOGGER.info("--表单输入数据预处理--");
        //模拟预处理

        return true;
    }
}
