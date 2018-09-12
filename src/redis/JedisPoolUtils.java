package redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {

	private static JedisPool pool = null;
	private static Jedis jedis = null;
	
	static{
		
		//加载配置文件,默认是从此类所在的包下取资源
		InputStream in = JedisPoolUtils.class.getResourceAsStream("redis.properties");
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//获得池子对象
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(Integer.parseInt(pro.get("redis.maxIdle").toString()));//最大闲置个数
		poolConfig.setMinIdle(Integer.parseInt(pro.get("redis.minIdle").toString()));//最小闲置个数
		poolConfig.setMaxTotal(Integer.parseInt(pro.get("redis.maxTotal").toString()));//最大连接数
		pool = new JedisPool(poolConfig,pro.getProperty("redis.url") , Integer.parseInt(pro.get("redis.port").toString()));
	}

	//获得jedis资源的方法
	public static Jedis getJedis(){
		jedis= pool.getResource();
		//jedis.auth("root");
		return jedis;
	}
	public void close(){
		if (jedis != null) {
			jedis.close();
		}
		if (pool != null) {
			pool.close();
		}
	}

//	@Test
//	public void test3() {
//		// 1.获得连接池配置对象
//		JedisPoolConfig config = new JedisPoolConfig();
//		// 最大连接数
//		config.setMaxTotal(Integer.parseInt(pro.get("redis.maxIdle").toString()));
//		// 最大空闲连接数
//		config.setMaxIdle(10);
//
//		// 2.获得连接池
//		JedisPool jp = new JedisPool(config, "47.93.204.33", 6379);
//		// 3.获得核心对象
//		Jedis jedis = null;
//		try {
//			jedis = jp.getResource();
//			jedis.auth("root");
//			// 设置数据
//			jedis.set("name2", "lisi");
//			// 提取数据
//			String name2 = jedis.get("name2");
//			System.out.println(name2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (jedis != null) {
//				jedis.close();
//			}
//			// 虚拟机关闭时释放连接池资源
//			if (jp != null) {
//				jp.close();
//			}
//		}
//	}

}
