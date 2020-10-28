package redisson.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * Notice: 分布式锁入参
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/9/25
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class LockArgs {
    private String lockName;
    private BizExecutor executor;
    private int leaseTime;
    private TimeUnit timeUnit;
    private long waitTime;
}
