package net.vv2.system.service;

import net.vv2.system.domain.TSysConfigMessageEntity;

import java.util.List;
import java.util.Map;

/**
 * @author zhongfs
 * @email zhongfs@asiainfo.com
 * @date 2020-04-17 18:58:08
 */
public interface TSysConfigMessageService {

    List<TSysConfigMessageEntity> query(String months, String projectId);

    List<String> getConfigValueList(String configType, String configKey);

    /**
     * 获取配置信息
     *
     * @param configType
     * @param configKey
     * @return
     */
    TSysConfigMessageEntity getConfigInfo(String configType, String configKey);

    /**
     * 获取配置信息，返回字符串
     *
     * @param configType
     * @param configKey
     * @return
     */
    String getConfigValue(String configType, String configKey);
}

