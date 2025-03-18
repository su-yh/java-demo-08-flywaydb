package com.suyh.app.datasource.properties;

import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.suyh.app.datasource.DataSourceEnums;
import com.suyh.app.datasource.HikariDataSourcePlus;
import lombok.Data;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author suyh
 * @since 2024-03-20
 */
@ConfigurationProperties(prefix = "biz.datasource")
@Data
@Validated
public class DynamicDataSourceProviderProperties implements DynamicDataSourceProvider, InitializingBean {
    @NestedConfigurationProperty
    @Valid
    private final Map<DataSourceEnums, HikariDataSourcePlus> multi = new HashMap<>();

    @Override
    public synchronized Map<String, DataSource> loadDataSources() {
        Map<String, DataSource> map = new HashMap<>();
        multi.forEach((k, v) -> map.put(k.getCode(), v));
        return map;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Collection<HikariDataSourcePlus> hikariDataSourcePluses = multi.values();
        for (HikariDataSourcePlus ds : hikariDataSourcePluses) {
            doFlyway(ds);
        }
    }

    private void doFlyway(HikariDataSourcePlus ds) throws Exception {
        BizFlywayProperties bizFlyway = ds.getFlyway();
        if (!bizFlyway.isEnabled()) {
            return;
        }

        String[] locations = ds.getFlyway().getLocations();
        FluentConfiguration cdsWebFlywayConfig = new FluentConfiguration();
        cdsWebFlywayConfig.baselineOnMigrate(true)
                .dataSource(ds)
                .locations(locations)
                .table("flyway_biz_history")
                .validateOnMigrate(true)
                .ignoreFutureMigrations(true)
                .outOfOrder(true);
        Flyway cdsWebFlyway = cdsWebFlywayConfig.load();
        FlywayMigrationInitializer flywayMigrationInitializer = new FlywayMigrationInitializer(cdsWebFlyway, null);
        flywayMigrationInitializer.afterPropertiesSet();
    }
}
