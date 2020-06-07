package net.vv2.system.mapper;


import net.vv2.system.domain.TSysConfigMessageEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhongfs
 * @email zhongfs@asiainfo.com
 * @date 2020-04-17 18:58:08
 */
@Mapper
public interface TSysConfigMessageMapper {

    List<TSysConfigMessageEntity> query(@Param("months") String months, @Param("projectId") String projectId);


    @Select("        select `id`,\n" +
            "        `config_name`,\n" +
            "        `config_type`,\n" +
            "        `config_key`,\n" +
            "        `config_value`,\n" +
            "        `api_url`,\n" +
            "        `status`,\n" +
            "        `remarks`,\n" +
            "        `column`\n" +
            "        from t_sys_config_message\n" +
            "        where status='1'\n" +
            "        and config_type=#{configType}\n" +
            "        and config_key=#{configKey}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "id"),
            @Result(column = "config_name", property = "configName"),
            @Result(column = "config_type", property = "configType"),
            @Result(column = "config_key", property = "configKey"),
            @Result(column = "config_value", property = "configValue"),
            @Result(column = "api_url", property = "apiUrl"),
            @Result(column = "status", property = "status"),
            @Result(column = "remarks", property = "remarks"),
            @Result(column = "column", property = "column")
    })
    TSysConfigMessageEntity querySysConfigReportByConfigTypeAndConfigKey(@Param("configType") String configType, @Param("configKey") String configKey);

}
