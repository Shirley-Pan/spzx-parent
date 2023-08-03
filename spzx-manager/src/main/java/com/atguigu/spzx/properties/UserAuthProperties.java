package com.atguigu.spzx.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;

/**
 * projectName: com.atguigu.spzx.properties
 *
 * @author: ppp
 * time: 2023/7/31 22:27
 * description:
 */
@Data
@ConfigurationProperties(prefix = "spzx.auth")
public class UserAuthProperties {
    private List<String> noAuthUrls;
}
