package net.vv2.system.domain;


import java.util.Objects;

/**
 * @author zhongfs
 * @email zhongfs@asiainfo.com
 * @date 2020-04-17 18:58:08
 */

public class TSysConfigMessageEntity {

    /**
     *
     */
    private Integer id;
    /**
     *
     */
    private String configName;
    /**
     *
     */
    private String configType;
    /**
     *
     */
    private String configKey;
    /**
     *
     */
    private String configValue;
    /**
     *
     */
    private String apiUrl;
    /**
     *
     */
    private String status;
    /**
     *
     */
    private String remarks;
    /**
     *
     */
    private String column;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

}
