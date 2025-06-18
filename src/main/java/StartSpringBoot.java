
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StartSpringBoot {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(StartSpringBoot.class, args);
        System.out.println("启动成功!!!");
    }

}

