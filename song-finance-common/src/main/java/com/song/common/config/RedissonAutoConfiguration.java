package com.song.common.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Config.class)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {

    @Autowired
    private RedissonProperties redssionProperties;

    /**
     * 哨兵模式自动装配
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "redisson.master-name")
    RedissonClient redissonSentinel() {
        Config config = new Config();
        SentinelServersConfig serverConfig = config
                .useSentinelServers()
                .addSentinelAddress(redssionProperties.getSentinelAddresses())
                .setMasterName(redssionProperties.getMasterName())
                .setTimeout(redssionProperties.getTimeout())
                .setMasterConnectionPoolSize(redssionProperties.getMasterConnectionPoolSize())
                .setSlaveConnectionPoolSize(redssionProperties.getSlaveConnectionPoolSize());

        if (StringUtils.isNotBlank(redssionProperties.getPassword())) {
            serverConfig.setPassword(redssionProperties.getPassword());
        }
        return Redisson.create(config);
    }

    /**
     * 单机模式自动装配
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "redisson.address")
    RedissonClient redissonSingle() {
        Config config = new Config();
        config.setCodec(new org.redisson.codec.JsonJacksonCodec());
        SingleServerConfig serverConfig = config
                .useSingleServer()
                .setAddress(redssionProperties.getAddress())
                //等待节点回复命令的时间。该时间从命令发送成功时开始计时。
                .setTimeout(redssionProperties.getTimeout())
                //设置对于master节点的连接池中连接数最大为50
                .setConnectionPoolSize(redssionProperties.getConnectionPoolSize())
                //  连接池的最小空闲连接数。当连接池中的连接数低于此值时，会尝试创建新连接。
                .setConnectionMinimumIdleSize(redssionProperties.getConnectionMinimumIdleSize());

        if (StringUtils.isNotBlank(redssionProperties.getPassword())) {
            serverConfig.setPassword(redssionProperties.getPassword());
        }

        return Redisson.create(config);
    }

    /**
     * 装配locker类，并将实例注入到RedissLockUtil中
     * @return
     */
//    @Bean
//    DistributedLocker distributedLocker(RedissonClient redissonClient) {
//        DistributedLocker locker = new RedissonDistributedLocker();
//        locker.setRedissonClient(redissonClient);
//        RedissLockUtil.setLocker(locker);
//        return locker;
//    }

}