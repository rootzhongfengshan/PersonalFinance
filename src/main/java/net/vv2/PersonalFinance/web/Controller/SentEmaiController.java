package net.vv2.PersonalFinance.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/PersonalFinance/sentemail")
public class SentEmaiController {

    @RequestMapping("sentMailWithCost")
    public String gotosaveCost(Model model){
        return "PersonalFinance/sentemail/SentMailWithCost";
    }
}
