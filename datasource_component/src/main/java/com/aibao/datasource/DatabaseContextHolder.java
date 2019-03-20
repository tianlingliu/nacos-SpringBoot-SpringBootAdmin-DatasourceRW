package com.aibao.datasource;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 当前线程需要的数据源切换，线程安全
 */
@Slf4j
public class DatabaseContextHolder {

    private static final ThreadLocal<DBEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        log.debug("change datasource use master");
        set(DBEnum.MASTER);
    }

    public static void slave() {
        //  轮询
        int index = counter.getAndIncrement() & 1;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DBEnum.SLAVE1);
            log.debug("change datasource use slave_1");
        } else {
            set(DBEnum.SLAVE2);
            log.debug("change datasource use slave_2");
        }

    }
}