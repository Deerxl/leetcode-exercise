package org.example.pipe.executor;

import org.example.pipe.context.PipelineContext;
import org.example.pipe.handler.ContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

/**
 * 管道执行器
 */
@Component
public class PipelineExecutor {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private ThreadPoolExecutor service = new ThreadPoolExecutor(5, 5, 5, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(
            1000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

    //引用 PipelineRouteConfig 中的 pipelineRouteMap
    //key: 上下文； value： 处理器
    @Resource
    private Map<Class<? extends PipelineContext>, List<? extends ContextHandler<? super PipelineContext>>> pipelineRouteMap;

    /**
     * 同步执行
     * @param context
     * @return
     */
    public boolean acceptSync(PipelineContext context) {
        Objects.requireNonNull(context, "上下文数据不能为null");
        //获取上下文对应的数据类型（可能存在多种类型的上下文）
        Class<? extends PipelineContext> dataType = context.getClass();

        //根据对应的上下文类类型，获取不同的数据处理管道集合
        List<? extends ContextHandler<? super PipelineContext>> pipe = pipelineRouteMap.get(dataType);
        if (CollectionUtils.isEmpty(pipe)) {
            LOGGER.info("{}的管道为空", dataType.getSimpleName());
            return false;
        }

        // 管道是否通畅
        boolean lastSuccess = true;
        for (ContextHandler<? super PipelineContext> handler : pipe) {
            try {
                lastSuccess = handler.handle(context);
            } catch (Exception e) {
                lastSuccess = false;
                LOGGER.error("[{}]处理异常，handler={}",context.getName(), handler.getClass().getSimpleName());
                throw new RuntimeException(e);
            }

            if (!lastSuccess) {
                break;
            }
        }


        return lastSuccess;
    }

    /**
     *  异步处理输入的上下文数据
     * @param context 上下文数据
     * @param callback  处理完成的回调
     */
    public void acceptAsync(PipelineContext context, BiConsumer<PipelineContext, Boolean> callback) {
        service.execute(()->{
            boolean success = acceptSync(context);

            if (callback != null) {
                callback.accept(context, success);
            }
        });
    }

}
