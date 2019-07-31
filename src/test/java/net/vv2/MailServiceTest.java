package net.vv2;

import net.vv2.PersonalFinance.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;
    @Test
    public void testSimpleMail() throws Exception {
       boolean flag= mailService.sentEmail("zhongfengshan@qq.com","test your test","hahahahhah");
       System.out.println(flag);

    }
}