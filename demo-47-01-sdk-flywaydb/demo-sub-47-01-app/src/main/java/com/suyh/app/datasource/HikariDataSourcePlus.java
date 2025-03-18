package com.suyh.app.datasource;

import com.suyh.app.datasource.properties.BizFlywayProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.validation.Valid;

/**
 * @author suyh
 * @since 2025-02-18
 */
@Data
public class HikariDataSourcePlus extends HikariDataSource {
    @NestedConfigurationProperty
    @Valid
    private final BizFlywayProperties flyway = new BizFlywayProperties();
}
