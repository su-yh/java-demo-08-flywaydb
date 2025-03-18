package com.suyh.app.datasource.properties;

import lombok.Data;

/**
 * @author suyh
 * @since 2024-02-23
 */
@Data
public class BizFlywayProperties {
    private boolean enabled = false;

    private String[] locations;
}
