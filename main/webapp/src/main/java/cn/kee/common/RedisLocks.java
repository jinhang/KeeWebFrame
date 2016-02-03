package cn.kee.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.kee.redis.RedisLock;

public class RedisLocks {

	private HashMap<String, RedisLock> lockMap = new HashMap<String, RedisLock>();

	private List<String> keys;

	private long time;

	public RedisLocks(List<String> keys, long time) {
		this.keys = keys;
		this.time = time;
	}

	public boolean lock() {
		for (int m = 0; m < keys.size(); m++) {
			String key = keys.get(m);
			if (!this.lockMap.containsKey(key)) {
				RedisLock lock = new RedisLock(key);
				this.lockMap.put(key, lock);
				if (!lock.lock(time)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean trylock() {
		for (int m = 0; m < keys.size(); m++) {
			String key = keys.get(m);
			if (!this.lockMap.containsKey(key)) {
				RedisLock lock = new RedisLock(key);
				this.lockMap.put(key, lock);
				if (!lock.trylock()) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean unlock() {
		for (Iterator<RedisLock> iterator = lockMap.values().iterator(); iterator
				.hasNext();) {
			RedisLock lock = iterator.next();
			lock.unlock();
		}
		return true;

	}

}
