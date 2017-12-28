package cn.will.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;


public class JedisTest {
    @Test
    public void testJedisSingle() {
        //创建jedis对象
        Jedis jedis = new Jedis("192.168.184.130", 6379);
        //调用jedis对象的方法，方法名称和redis的命令一致
        jedis.set("key1", "Hello jedis");
        String str = jedis.get("key1");
        System.out.println(str);
        //关闭jedis
        jedis.close();
    }

    //使用redis连接池
    @Test
    public void testJedisPool() {
        //创建连接池对象
        JedisPool jedisPool = new JedisPool("192.168.184.130", 6379);
        //从连接池中获取jedis对象
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get("key1");
        System.out.println(str);
        //关闭连接池，jedis
        jedis.close();
        jedisPool.close();
    }

    //测试集群
    @Test
    public void testJedisCluster() {
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.184.130", 7001));
        nodes.add(new HostAndPort("192.168.184.130", 7002));
        nodes.add(new HostAndPort("192.168.184.130", 7003));
        nodes.add(new HostAndPort("192.168.184.130", 7004));
        nodes.add(new HostAndPort("192.168.184.130", 7005));
        nodes.add(new HostAndPort("192.168.184.130", 7006));

        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("key1", "hello redis-cluster");
        String str = cluster.get("key1");
        System.out.println(str);

        cluster.close();
    }

    //spring整合jedis
    @Test
    public void testSpringJedisSingle() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
        Jedis jedis = pool.getResource();
        String str = jedis.get("key1");
        System.out.println(str);
        jedis.close();
        pool.close();
    }

    //spring 集群版
    @Test
    public void testSpringJedisCluster() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisCluster cluster = (JedisCluster) applicationContext.getBean("redisClient");
        String str = cluster.get("key1");
        System.out.println(str);
        cluster.close();
    }
}
