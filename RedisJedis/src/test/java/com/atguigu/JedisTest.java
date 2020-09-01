package com.atguigu;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

import java.util.List;

public class JedisTest {
    @Test
    //jedis连接Redis
    public void test1(){
        //创建连接对象
        Jedis jedis = new Jedis("192.168.1.102", 6379);
        //获取连接
        String ping = jedis.ping();
        System.out.println("ping="+ping);
        Long l = jedis.lpush("Color", "Red", "Blue", "Yellow", "Green", "Black", "White", "Grey");
        System.out.println(l);
        List<String> res = jedis.lrange("Color", 0, -1);
        for (String re : res) {
            System.out.print(re+"    ");
        }
        //关闭连接
        jedis.close();
    }
    @Test
    public void test2(){
        //JedisPool
        String host = "192.168.1.102";
        int port = Protocol.DEFAULT_PORT;
        //创建连接池对象
        JedisPool jp = new JedisPool(host, port);
        //获取Jedis对象连接Redis
        Jedis res = jp.getResource();
        String ping = res.ping();
        Long abc = res.lpush("Character", "A", "C", "E", "F");
        List<String> character = res.lrange("Character", 0, -1);
        for (String s : character) {
            System.out.println(s);
        }
        //关闭连接池
        jp.close();
    }
}
