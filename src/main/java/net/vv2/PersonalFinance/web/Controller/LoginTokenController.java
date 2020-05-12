package net.vv2.PersonalFinance.web.Controller;

import com.xiaoleilu.hutool.crypto.SecureUtil;
import net.vv2.baby.domain.User;
import net.vv2.baby.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.converter.StringHttpMessageConverter;

import javax.servlet.http.HttpSession;

/**
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-10 11:10
 **/
@Controller
@SessionAttributes("user")
@RequestMapping("/auth")
public class LoginTokenController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    /**
     * 用户登陆
     *
     * @param username
     * @param password
     * @param mv
     * @return 登陆成功后返回USER
     */
    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    //@PostMapping(value = "/user/login")
    @RequestMapping(value = "loginfrom", method = RequestMethod.POST)
    @ResponseBody
    public void LoginFrom(String username,
                          String password,
                          String uuid) {
        System.out.println("开始验证登陆------------");
        User user = userService.selectUserByNameAndPassword(username, SecureUtil.md5(password));
        System.out.println("验证登陆用户和法性------------");
        String msg = "";
        String url = "";
        if (user != null) {
            msg = user.getName() + " 欢迎您登陆您成功！";

        } else {

        }


    }


    @RequestMapping("logout")
    public String logout(SessionStatus sessionStatus, Model model) {

        sessionStatus.setComplete();//清空SESSION
        String msg = "您已成功退出！";
        String url = "<meta http-equiv=\"refresh\" content=\"1;url=login\">";

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        return "success";

    }


}
