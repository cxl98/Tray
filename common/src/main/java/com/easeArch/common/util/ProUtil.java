package com.easeArch.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ProUtil implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProUtil.class);
    private static final String CONFIG = "/home/cxl/桌面/周计划/cxl/托盘/Tray/common/src/main/resources/keyboard.properties";
    private Map<String, Long> configFileModifyData = new HashMap<>();
    private Map<String, String> SYSTEM_CONFIG = new ConcurrentHashMap<>();

    public Map<String, String> getSystemConfig() {
        return SYSTEM_CONFIG;
    }

    @Override
    public void run() {
        int checkDelay = 5 * 1000;
        LOGGER.info("启动监听配置文件");
        while (true) {
            try {
                this.loadAllConfigFiles();
                Thread.sleep(checkDelay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadAllConfigFiles() {
        File file = new File(CONFIG);
        String fullPath = file.getAbsolutePath();
        Long value = configFileModifyData.get(fullPath);
        if (value == null || value != file.lastModified()) {
            LOGGER.info("加载配置文件" + file);
            loadPropertiesFile(file);
            configFileModifyData.put(fullPath, file.lastModified());
        }


    }

    private void loadPropertiesFile(File file) {
        Properties props = new Properties();
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            props.load(is);
            Set<String> strings = props.stringPropertyNames();
            for (String key : strings) {
                String value = props.getProperty(key);
                LOGGER.info("load properties:" + key + "==" + value);
                SYSTEM_CONFIG.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProUtil proUtil = new ProUtil();
        proUtil.loadAllConfigFiles();
    }
}
