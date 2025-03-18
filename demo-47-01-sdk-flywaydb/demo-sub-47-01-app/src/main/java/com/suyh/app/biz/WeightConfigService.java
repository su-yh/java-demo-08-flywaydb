package com.suyh.app.biz;

import com.suyh.app.mp.mapper.WeightConfigMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author suyh
 * @since 2025-03-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WeightConfigService {
    private final WeightConfigMapper weightConfigMapper;
}
