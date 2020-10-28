package redisson.common;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.redisson.api.RScript;
import org.springframework.core.io.Resource;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.scripting.support.StaticScriptSource;

import java.io.IOException;
import java.util.List;

/**
 * Notice: script输入参数
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/9/25
 * @since 1.0
 */
@Data
public class RedisScript<T> {
    private final Object shaModifiedMonitor=new Object();
    private ScriptSource scriptSource;
    private String sha1;
    private Class<T> resultType;

    public RedisScript(){

    }

    public RedisScript(String location, String sha1, Class<T> resultType, String scriptText) {
        this.setScriptText(location);
        this.sha1 = sha1;
        this.resultType = resultType;
    }

    public String getScript()  {
        try {
            return this.scriptSource.getScriptAsString();
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading script text", e);
        }
    }

    /**
     * 获取hash值
     * @return hash值
     */
    public String getSha1() {
        synchronized (shaModifiedMonitor) {
            if (sha1 == null || scriptSource.isModified()) {
                this.sha1 = DigestUtils.sha1Hex(getScript());
            }
            return sha1;
        }
    }

    /**
     * 设置脚本资源
     * @param scriptText 脚本资源
     */
    public void setScriptText(String scriptText){
        this.scriptSource=new StaticScriptSource(scriptText);
    }

    /**
     * 设置脚本资源
     * @param location 脚本资源
     */
    public void setLocation(Resource location){
        this.scriptSource=new ResourceScriptSource(location);
    }
    /**
     * 类型转换
     * @param javaType java类型
     * @return 枚举类型
     */
    public static RScript.ReturnType getReturnType(Class<?> javaType){
        if(javaType==null){
            return RScript.ReturnType.STATUS;
        }
        if (javaType.isAssignableFrom(List.class)) {
            return RScript.ReturnType.MULTI;
        }
        if (javaType.isAssignableFrom(Boolean.class)) {
            return RScript.ReturnType.BOOLEAN;
        }
        if (javaType.isAssignableFrom(Long.class)) {
            return RScript.ReturnType.INTEGER;
        }
        return RScript.ReturnType.VALUE;
    }
}
