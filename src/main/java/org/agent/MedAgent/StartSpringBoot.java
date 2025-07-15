package org.agent.MedAgent;

import org.agent.MedAgent.config.StartBeforeCheck;
import org.agent.MedAgent.store.MongoBlockingMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.agent.MedAgent")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StartSpringBoot {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(StartSpringBoot.class, args);
        StartBeforeCheck check = run.getBean(StartBeforeCheck.class);
        check.CheckRedisKey();
        check.CheckMongodb();
        System.out.println("Springboot启动成功!!!");
        String currentAbsolutePath = System.getProperty("user.dir");
        System.out.println("当前工作目录：" + currentAbsolutePath);
        MongoBlockingMessageHandler handler = run.getBean(MongoBlockingMessageHandler.class);
        handler.StartHandlingMongoBlockingQueue();
        System.out.println("Redis消息队列消费者线程启动成功！");
    }

}

