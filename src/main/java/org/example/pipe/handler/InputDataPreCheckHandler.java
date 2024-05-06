package org.example.pipe.handler;

import org.example.pipe.context.InstanceBuildContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;


import java.util.Map;

/**
 * 管道 处理器
 * 作用：输入数据校验
 */
@Component
public class InputDataPreCheckHandler implements ContextHandler<InstanceBuildContext> {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean handle(InstanceBuildContext context) {

        LOGGER.info("--输入数据校验--");
        Map<String, Object> form = context.getFormInput();

        if(form.isEmpty()) {
            context.setErrorMsg("表单输入数据不能为空");
            return false;
        }
        String instanceName = (String) form.get("instanceName");
        if (StringUtils.isEmpty(instanceName)) {
            context.setErrorMsg("表单输入数据必须包含实例名称");
            return  false;
        }

        return true;
    }
}
