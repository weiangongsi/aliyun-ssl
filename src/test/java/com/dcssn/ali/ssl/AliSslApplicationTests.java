package com.dcssn.ali.ssl;

import com.dcssn.ali.ssl.job.ScheduleTask;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest
public class AliSslApplicationTests {

    @Resource
    private ScheduleTask scheduleTask;

    @Test
    public void test() throws Exception {
        scheduleTask.testScheduleTask1();
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String appId="wx09cb18610ddb04ac";
        String appSecret="b78db067efbbe7c4706ea6484431c54a";
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret, String.class);
        System.out.println(forEntity);
    }
}
