package net.vv2.personal.finance.web.controller;

import net.vv2.personal.finance.constants.Constants;
import net.vv2.system.service.TSysConfigMessageService;
import net.vv2.util.HandleFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhongfs
 */
@Controller
@RequestMapping(value = "PersonalFinance/downloadFile")
public class DownloadFileController {
    @Autowired
    private TSysConfigMessageService myTSysConfigMessageService;
    @Value("${upload.quotaFile}")
    private String Quota_FILE_PATH;

    @RequestMapping("case")
    public void downloadFile(String configKey, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String fileName = myTSysConfigMessageService.getConfigValue(Constants.FOLDER_LOCATE, configKey);
        HandleFileUtils.DownExcelTemplate(response, Quota_FILE_PATH, fileName);
    }
}
