package net.vv2.system.service.impl;

import net.vv2.system.domain.TSysConfigMessageEntity;
import net.vv2.system.mapper.TSysConfigMessageMapper;
import net.vv2.system.service.TSysConfigMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author zhongfs
 */
@Service
@Transactional
public class TSysConfigMessageServiceImpl implements TSysConfigMessageService {

    @Resource
    private TSysConfigMessageMapper myTSysConfigMessageMapper;


    @Override
    public List<TSysConfigMessageEntity> query(String months, String projectId) {
        List<TSysConfigMessageEntity> tSysConfigReport = myTSysConfigMessageMapper.query(months, projectId);
        return tSysConfigReport;
    }


    @Override
    public List<String> getConfigValueList(String configType, String configKey) {
        TSysConfigMessageEntity tSysConfigReport = myTSysConfigMessageMapper.querySysConfigReportByConfigTypeAndConfigKey(configType, configKey);
        if (tSysConfigReport == null) {
            throw new RuntimeException("根据" + configKey + "找不到相对应的度量项！！");
        }
        String measureElementString = tSysConfigReport.getConfigValue();
        return new ArrayList<>(Arrays.asList(StringUtils.split(measureElementString, ",")));
    }

    /**
     * 获取配置信息
     *
     * @param configType
     * @param configKey
     * @return
     */
    @Override
    public TSysConfigMessageEntity getConfigInfo(String configType, String configKey) {
        TSysConfigMessageEntity tSysConfigReport = myTSysConfigMessageMapper.querySysConfigReportByConfigTypeAndConfigKey(configType, configKey);
        if (tSysConfigReport == null) {
            throw new RuntimeException("根据" + configKey + "找不到相对应的度量项！！");
        }
        return tSysConfigReport;
    }

    @Override
    public String getConfigValue(String configType, String configKey) {
        TSysConfigMessageEntity tSysConfigReport = myTSysConfigMessageMapper.querySysConfigReportByConfigTypeAndConfigKey(configType, configKey);
        if (tSysConfigReport == null) {
            throw new RuntimeException("根据" + configKey + "找不到相对应的度量项！！");
        }
        return tSysConfigReport.getConfigValue();
    }

}