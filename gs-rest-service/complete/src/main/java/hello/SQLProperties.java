package hello;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties
public class SQLProperties {

    private Map<String, Object> sqls = new HashMap<String, Object>();

    public Map<String, Object> getSqls() {
        return sqls;
    }
}
