package com.suyh.app.datasource.properties;

import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.suyh.app.datasource.DataSourceNames;
import com.suyh.app.datasource.HikariDataSourceShow;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author suyh
 * @since 2024-03-20
 */
@ConfigurationProperties(prefix = "biz.datasource")
@Data
@Validated
public class DynamicDataSourceProviderProperties implements DynamicDataSourceProvider {
    @NotNull
    @NestedConfigurationProperty
    private HikariDataSourceShow master;

    @NestedConfigurationProperty
    private HikariDataSourceShow slave;

    private static Map<String, DataSource> mapDatasource;

    @Override
    public synchronized Map<String, DataSource> loadDataSources() {
        if (mapDatasource == null) {
            mapDatasource = new HashMap<>();

            mapDatasource.put(DataSourceNames.MASTER, master);
            if (slave != null) {
                mapDatasource.put(DataSourceNames.SLAVE, slave);
            }
        }
        return mapDatasource;
    }
}
