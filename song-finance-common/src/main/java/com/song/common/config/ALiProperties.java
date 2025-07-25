package com.song.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ali")
@Data
public class ALiProperties {

    private String accessKeyId;

    private String accessKeySecret;

    private ALiSmsConfig sms;

    @Data
    public static class ALiSmsConfig {
        /**
         * 是否启用
         */
        private Boolean enable;
        /**
         * 区域
         */
        private String region;
        /**
         * 端点
         */
        private String endpoint;
    }
}