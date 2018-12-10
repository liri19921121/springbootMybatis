import com.activemq.producer;
import com.pojo.UserInfo;
import com.service.impl.UserServiceImpl;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SMApplication.class)
@ContextConfiguration
public class SMApplicationTest {

    @Resource
    private producer producer;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testJms() {
            Destination destination = new ActiveMQQueue("springboot.queue.test");
            UserInfo userInfo = userService.getUserByid(1L);
            producer.sendMessage(destination,userInfo);
    }

}
