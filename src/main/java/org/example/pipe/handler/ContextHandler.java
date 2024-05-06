package org.example.pipe.handler;


import org.example.pipe.context.PipelineContext;

/**
 * 管道中的上下文处理器
 * 设计成泛型，是为了用于处理不同类型的上下文数据流，做扩展使用
 */
public interface ContextHandler< T extends PipelineContext> {

    /**
     * 处理输入的上下文数据
     * @param context  处理时的上下文数据
     * @return  返回 true 则表示由下一个 ContextHandler 继续处理，返回 false 则表示处理结束
     */
    boolean handle(T context);
}
