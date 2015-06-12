package hello;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TestHealthIndicator extends AbstractHealthIndicator {

    private static Log logger = LogFactory.getLog(TestHealthIndicator.class);

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.withDetail("test", "this is test health");
    }
}
