package com.suyh.app.datasource;

import lombok.Getter;

/**
 * @author suyh
 * @since 2025-03-18
 */
@Getter
public enum DataSourceEnums {
    MASTER(DataSourceNames.MASTER), SLAVE(DataSourceNames.SLAVE),
    ;

    private final String code;

    DataSourceEnums(String code) {
        this.code = code;
    }
}
