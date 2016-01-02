package org.kuali.rice.web.health;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.health.HealthCheck;

/**
 * A health check that checks whether the given {@link Gauge} (which should represent percentage usage of a database
 * connection pool) returns a value that is below the supplied unhealthy threshold.
 *
 * @author Eric Westfall (ewestfal@gmail.com)
 */
public class DatabaseConnectionPoolHealthCheck extends HealthCheck {

    private final Gauge<Double> gauge;
    private final double unhealthyThreshold;

    public DatabaseConnectionPoolHealthCheck(Gauge<Double> gauge, double unhealthyThreshold) {
        this.gauge = gauge;
        this.unhealthyThreshold = unhealthyThreshold;
    }

    @Override
    protected Result check() throws Exception {
        Double value = gauge.getValue();
        if (value >= unhealthyThreshold) {
            return Result.unhealthy("Database connection pool usage ratio of " + value + " was greater than or equal to threshold of " + unhealthyThreshold);
        }
        return Result.healthy();
    }


}
