package cn.sleepybear.gaokaorecommendzhiyuan.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2023/06/30 15:10
 */
@Configuration
@Data
public class MyConfig {
    @Value("${my-config.data-dir}")
    private String dataDir;
}
