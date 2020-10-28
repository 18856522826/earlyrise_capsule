package redisson;

import org.redisson.api.*;
import org.redisson.client.codec.Codec;
import org.springframework.util.Assert;
import redisson.common.LockArgs;
import redisson.common.RedisScript;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/9/21
 * @since 1.0
 */
public class RedisTemplate<V> {
    /**
     * redisson客户端
     */
    private final RedissonClient redissonClient;

    /**
     * redisson编码
     */
    private final Codec codec;

    public RedisTemplate(RedissonClient redissonClient){
        this(redissonClient,redissonClient.getConfig().getCodec());
    }
    public RedisTemplate(RedissonClient redissonClient,Codec codec){
        Assert.notNull(redissonClient);
        Assert.notNull(codec);
        this.codec = codec;
        this.redissonClient=redissonClient;
    }

    /**
     * 通用set方法
     * @param key key
     * @param value 值
     */
    public void set(String key, V value){
        RBucket<V> rBucket = redissonClient.getBucket(key,this.codec);
        rBucket.set(value);
    }

    /**
     * 通用set方法
     * @param key key
     * @param value 值
     * @param time 过期时间
     * @param timeUnit 时间单位
     */
    public  void set(String key, V value, long time, TimeUnit timeUnit){
        RBucket<V> rBucket =redissonClient.getBucket(key,this.codec);
        rBucket.set(value,time,timeUnit);
    }


    /**
     * 通用get方法
     * @param key key
     * @return 键值
     */
    public  V get(String key){
        RBucket<V> rBucket =redissonClient.getBucket(key,this.codec);
        return rBucket.get();
    }

    /**
     * 通用加锁方法
     * @param lockArgs 加锁参数
     * @throws InterruptedException 异常
     */
    public void lock(LockArgs lockArgs) throws InterruptedException {
        RLock rLock = redissonClient.getLock(lockArgs.getLockName());
        boolean aquire = rLock.tryLock(lockArgs.getWaitTime(),lockArgs.getLeaseTime(),lockArgs.getTimeUnit());
        if(!aquire){return;}
        try {
            lockArgs.getExecutor().execute();
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 通用脚本执行
     * @param script 脚本资源
     * @param list key列表
     * @param objects 参数
     */
    public <R> R exec(RedisScript<R> script, List<Object> list, Object... objects){
        RScript rscript=redissonClient.getScript(this.codec);
        if(rscript.scriptExists(script.getSha1()).get(0)){
            return rscript.evalSha(RScript.Mode.READ_WRITE,script.getSha1(), RedisScript.getReturnType(script.getResultType()),list,objects);
        }else{
            return rscript.eval(RScript.Mode.READ_WRITE,script.getScript(), RedisScript.getReturnType(script.getResultType()),list,objects);
        }
    }

    /**
     * key值加一
     * @param key key
     */
    public void increment(String key){
        RAtomicLong rAtomicLong=redissonClient.getAtomicLong(key);
        rAtomicLong.incrementAndGet();
    }

    /**
     *
     * @param key key
     * @param delta 参数
     */
    public void incrementBy(String key,long delta){
        RAtomicLong rAtomicLong=redissonClient.getAtomicLong(key);
        rAtomicLong.addAndGet(delta);
    }

    /**
     * 判断key值是否存在
     * @param key key
     * @return 判断
     */
    public boolean isKey(String key){
        RBucket<String> rBucket=redissonClient.getBucket(key,this.codec);
        return rBucket.isExists();
    }

    /**
     * 删除key
     * @param key key
     */
    public void removeKey(String key){
        RBucket<V> rBucket=redissonClient.getBucket(key,this.codec);
        rBucket.delete();
    }

    /**
     * listSet操作
     * @param key key
     * @param value value
     */
    public void listPush(String key,V value){
        RList<V> rList =redissonClient.getList(key,this.codec);
        rList.add(value);
    }

    /**
     * listGet操作
     * @param key key
     * @return value
     */
    public V listGet(String key){
        RList<V> rList =redissonClient.getList(key,this.codec);
        return  rList.get(rList.size()-1);
    }
}
