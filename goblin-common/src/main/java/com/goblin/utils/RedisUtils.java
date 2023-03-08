package com.goblin.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/12/2
 */
@Component
public class RedisUtils {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 给一个指定的key值附加过期时间
     *
     * @param key  指定key
     * @param time 过期时间，秒
     * @return 是否成功
     */
    public boolean expire(String key, long time) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, time, TimeUnit.SECONDS));
    }

    /**
     * 根据key获取过期时间
     *
     * @param key 指定key
     * @return 过期时间
     */
    public long getTime(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 指定key
     * @return 是否存在
     */
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 移除指定key的过期时间
     *
     * @param key 指定key
     * @return 是否移除成功
     */
    public boolean persist(String key) {
        return redisTemplate.boundValueOps(key).persist();
    }


    /**
     * 根据key获取值
     *
     * @param key 指定key
     * @return 对应值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 将值放入缓存
     *
     * @param key   指定key
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将值放入缓存并设置时间
     *
     * @param key   指定key
     * @param value 值
     * @param time  时间(秒) -1为无期限
     */
    public void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 批量添加 key (重复的键会覆盖)
     *
     * @param keyAndValue 批量键值对
     */
    public void batchSet(Map<String, Object> keyAndValue) {
        redisTemplate.opsForValue().multiSet(keyAndValue);
    }

    /**
     * 批量添加 key-value 只有在键不存在时,才添加
     * map 中只要有一个key存在,则全部不添加
     *
     * @param keyAndValue 批量键值对
     */
    public void batchSetIfAbsent(Map<String, Object> keyAndValue) {
        redisTemplate.opsForValue().multiSetIfAbsent(keyAndValue);
    }

    /**
     * 对一个key-value的值进行加减操作,
     * 如果该key不存在 将创建一个key 并赋值该number
     * 如果key存在,但 value不是长整型,将报错
     *
     * @param key    指定key
     * @param number 待增数值
     */
    public Long increment(String key, long number) {
        return redisTemplate.opsForValue().increment(key, number);
    }

    /**
     * 对一个key-value的值进行加减操作,
     * 如果该key不存在将创建一个key并赋值该number
     * 如果key存在,但value不是 纯数字,将报错
     *
     * @param key    指定key
     * @param number 待增数值
     */
    public Double increment(String key, double number) {
        return redisTemplate.opsForValue().increment(key, number);
    }

    //- - - - - - - - - - - - - - - - - - - - -  set类型 - - - - - - - - - - - - - - - - - - - -

    /**
     * 将数据放入set缓存
     *
     * @param key   键
     * @param value 值
     */
    public void sSet(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取变量中的值
     *
     * @param key 键
     * @return 对应Set集合
     */
    public Set<Object> members(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取变量中指定个数的元素
     *
     * @param key   键
     * @param count 随机个数
     * @return 随机元素
     */
    public List<Object> randomMembers(String key, long count) {
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 随机获取变量中的元素
     *
     * @param key 键
     * @return 随机元素
     */
    public Object randomMember(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 弹出变量中的元素
     *
     * @param key 键
     * @return 弹出元素
     */
    public Object pop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 获取变量中值的长度
     *
     * @param key 键
     * @return 值元素长度
     */
    public long size(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    ///**
    // * 根据value从一个set中查询,是否存在
    // *
    // * @param key   键
    // * @param value 值
    // * @return true 存在 false不存在
    // */
    //public boolean sHasKey(String key, Object value) {
    //    return redisTemplate.opsForSet().isMember(key, value);
    //}

    /**
     * 检查给定的元素是否在变量中。
     *
     * @param key 键
     * @param obj 元素对象
     * @return 是否存在
     */
    public boolean isMember(String key, Object obj) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, obj));
    }

    /**
     * 转移变量的元素值到目的变量。
     *
     * @param key     键
     * @param value   元素对象
     * @param destKey 目前键
     * @return 是否移动成功
     */
    public boolean move(String key, String value, String destKey) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().move(key, value, destKey));
    }

    /**
     * 批量移除set缓存中元素
     *
     * @param key    键
     * @param values 待移除值
     */
    public void remove(String key, Object... values) {
        redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 通过给定的key求2个set变量的差值
     *
     * @param key     键
     * @param destKey 键
     * @return 差值
     */
    public Set<Set> difference(String key, String destKey) {
        return Collections.singleton(redisTemplate.opsForSet().difference(key, destKey));
    }


    //- - - - - - - - - - - - - - - - - - - - -  hash类型 - - - - - - - - - - - - - - - - - - - -

    /**
     * 加入缓存
     *
     * @param key 键
     * @param map hash键值
     */
    public void add(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 获取key下的hash值
     *
     * @param key 键
     * @return 对应hash值
     */
    public Map<Object, Object> getHashEntries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 验证指定key下有没有指定的hashKey
     *
     * @param key     key
     * @param hashKey hashKey
     * @return 是否存在
     */
    public boolean hashKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 获取指定key的值
     *
     * @param key     key
     * @param hashKey hashKey
     * @return 指定key对应值
     */
    public Object getMapObject(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 删除指定 hash的HashKey
     *
     * @param key      key
     * @param hashKeys hashKey
     * @return 删除成功的数量
     */
    public Long delete(String key, String... hashKeys) {
        return redisTemplate.opsForHash().delete(key, (Object) hashKeys);
    }

    /**
     * 给指定hash的hashKey做增减操作
     *
     * @param key     key
     * @param hashKey hashKey
     * @param number  增减数值
     * @return Long
     */
    public Long increment(String key, String hashKey, long number) {
        return redisTemplate.opsForHash().increment(key, hashKey, number);
    }

    /**
     * 给指定hash的hashKey做增减操作
     *
     * @param key     key
     * @param hashKey hashKey
     * @param number  增减数值
     * @return Long
     */
    public Double increment(String key, String hashKey, Double number) {
        return redisTemplate.opsForHash().increment(key, hashKey, number);
    }

    /**
     * 获取key下的所有hashKey字段
     *
     * @param key key
     * @return Set<Object>
     */
    public Set<Object> hashKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取指定hash下面的键值对数量
     *
     * @param key key
     * @return Long
     */
    public Long hashSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    //- - - - - - - - - - - - - - - - - - - - -  list类型 - - - - - - - - - - - - - - - - - - - -

    /**
     * 获取集合指定位置的值。
     *
     * @param key   指定key
     * @param index 指定位置
     * @return 对应值
     */
    public Object index(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取指定区间的值。
     *
     * @param key   指定key
     * @param start 开始区间
     * @param end   结束区间
     * @return List<Object>
     */
    public List<Object> range(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 把最后一个参数值放到指定集合的第一个出现中间参数的前面，
     * 如果中间参数值存在的话。
     *
     * @param key   指定key
     * @param pivot
     * @param value
     */
    public void leftPush(String key, String pivot, String value) {
        redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    /**
     * 向左边批量添加参数元素。
     *
     * @param key    指定key
     * @param values 待添加元素
     */
    public void leftPushAll(String key, String... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 向左边批量添加参数元素。
     *
     * @param key    指定key
     * @param values 待添加元素
     */
    public void rightPushAll(String key, String... values) {
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 向已存在的集合中添加元素。
     *
     * @param key   指定key
     * @param value 待添加元素
     */
    public void rightPushIfPresent(String key, Object value) {
        redisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    /**
     * 获取指定key对应list长度
     *
     * @param key 指定key
     * @return 对应list长度
     */
    public long listLength(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 移除集合中的左边第一个元素。
     *
     * @param key 指定key
     */
    public Object leftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移除集合中左边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
     *
     * @param key     指定key
     * @param timeout 等待时间
     * @param unit    时间单位
     */
    public Object leftPop(String key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    /**
     * 移除集合中右边的元素。
     *
     * @param key 指定key
     */
    public Object rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移除集合中右边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
     *
     * @param key     指定key
     * @param timeout 等待时间
     * @param unit    时间单位
     */
    public Object rightPop(String key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().rightPop(key, timeout, unit);
    }
}
