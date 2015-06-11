package hello;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class OasisConfiguration{

    @Bean(name="dsOasis")
    @ConfigurationProperties(prefix="spring.oasis.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcOasis")
    public JdbcTemplate jdbcTemplate(DataSource dsOasis) {
        return new JdbcTemplate(dsOasis);
    }
}
