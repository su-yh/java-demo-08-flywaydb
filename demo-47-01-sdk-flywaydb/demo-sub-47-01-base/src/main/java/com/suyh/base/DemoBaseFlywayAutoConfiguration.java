package com.suyh.base;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author suyh
 * @since 2025-03-18
 */
@AutoConfiguration
public class DemoBaseFlywayAutoConfiguration {

    @ConditionalOnSingleCandidate(DataSource.class)
    @Bean("baseSystemFlyway")
    public FlywayMigrationInitializer baseSystemFlyway(DataSource dataSource) {
        String[] locations = {"classpath:/sqls/system/mysql"};
        FluentConfiguration cdsWebFlywayConfig = new FluentConfiguration();
        cdsWebFlywayConfig.baselineOnMigrate(true)
                .dataSource(dataSource)
                .locations(locations)
                .table("flyway_system_history")
                .validateOnMigrate(true)
                .ignoreFutureMigrations(true)
                .outOfOrder(true);
        Flyway cdsWebFlyway = cdsWebFlywayConfig.load();
        return new FlywayMigrationInitializer(cdsWebFlyway, null);
    }

}
