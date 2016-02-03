package com.kee.redis;
import java.util.Random;
 


import com.wfs.dialect.redis.JRClient;
import com.wfs.engine.common.EngineHelper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
 
/**
 * @author Qindebu
 */
public class RedisLock {
    //加锁标志
    public static final String LOCKED = "TRUE";
    public static final long ONE_MILLI_NANOS = 1000000L;
    //默认超时时间（毫秒）
    public static final long DEFAULT_TIME_OUT = 0;
    public static JedisPool pool;
    public static final Random r = new Random();
    //锁的超时时间（秒），过期删除
    public static final int EXPIRE = 600*3;
    
    public static final int LOCKTIME = 10000;
    
    public static JRClient jrc =  EngineHelper.getEngine().getJrc().getDefaultClient();
    private Jedis jedis;
    private String key;
    //锁状态标志
    private boolean locked = false;
 
    public RedisLock(String key) {
        this.key = key;
    }
 
    public boolean lock(long timeout,int expire) {
        long nano = System.nanoTime();
        timeout *= ONE_MILLI_NANOS;
        try {
            this.jedis = jrc.getJedis();
            while ((System.nanoTime() - nano) < timeout) {
                if (jedis.setnx(key, LOCKED) == 1) {
                    jedis.expire(key, expire);
                    locked = true;
                    return locked;
                }
                // 短暂休眠，nano避免出现活锁
                Thread.sleep(3, r.nextInt(500));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }finally{
        	jrc.close(jedis);
        }
        return false;
    }
    
    
    public boolean lock(long timeout) {
    	return lock(timeout,EXPIRE);
    }
    
    public boolean trylock(int expire) {
		try {
            this.jedis = jrc.getJedis();
		    if (jedis.setnx(key, LOCKED) == 1) {
		        jedis.expire(key, expire);
		        locked = true;
		        return locked;
		    }    
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
        	jrc.close(jedis);
        }
        return false;
    }
    
    public boolean trylock() {
    	return trylock(EXPIRE);
    }
    
    public boolean lock() {
        return lock(DEFAULT_TIME_OUT);
    }

    public void forceUnlock() {
        try {
       	    this.jedis = jrc.getJedis();
            jedis.del(key);
        }
        catch (Exception e) {
			e.printStackTrace();
		} finally {
        	jrc.close(jedis);
        }
    }
    
    // 无论是否加锁成功，必须调用
    public void unlock() {
        try {
       	    this.jedis = jrc.getJedis();
            if (locked){
                jedis.del(key);
            }
        }catch (Exception e) {
			e.printStackTrace();
		} finally {
        	jrc.close(jedis);
        }
    }
}