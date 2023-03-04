package com.example.springbootsample.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelCastConfig

{
    public Config config()
    {
        return  new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(new MapConfig().setTimeToLiveSeconds(30000));

    }
}
