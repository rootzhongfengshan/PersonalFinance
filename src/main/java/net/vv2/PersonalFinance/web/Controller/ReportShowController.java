package net.vv2.PersonalFinance.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "PersonalFinance/report")
public class ReportShowController {

    @RequestMapping(value = "index")
    public String getAccessString(Model model) {
        return "PersonalFinance/report/index";
    }
}
