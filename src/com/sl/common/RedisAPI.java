package com.sl.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisAPI {
	public JedisPool jedisPool; //redis 连接池对象

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
	
	/**
	 * set key,value to redis
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, String value){
		try {
			Jedis jedis = jedisPool.getResource();
			jedis.set(key, value);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//返回到连接池
		}
		return false;
	}
	
	/**
	 * 判断某个key是否存在
	 * @param key
	 * @return
	 */
	public boolean exists(String key){
		try {
			Jedis jedis = jedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//返回到连接池
		}
		return false;
	}
	
	/**
	 * 根据key获取数据
	 * @param key
	 * @return
	 */
	public String get(String key){
		String value = null;
		try {
			Jedis jedis = jedisPool.getResource();
			value = jedis.get(key);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//返回到连接池
		}
		return value;
	}
	
	@SuppressWarnings("deprecation")
	public static void returnResouce(JedisPool jedisPool, Jedis jedis){
		if (jedis != null){
			jedisPool.returnResource(jedis);
		}
	}
}
