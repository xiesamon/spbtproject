package com.samon.spbt.listener;

import com.samon.spbt.filter.CustomFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听器
 */
@WebListener
public class Customlister implements ServletRequestListener {

    private final static Logger log = LoggerFactory.getLogger(Customlister.class);

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("监听器：销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("监听器：初始化");
    }

}
