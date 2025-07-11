package Test.org.agent.MedAgent;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.agent.MedAgent.StartSpringBoot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest(classes = StartSpringBoot.class)
class TestChat {
    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Test
    void testChat(){
        String answer = openAiChatModel.chat("Say 'Hello World'");
        System.out.println(answer); // Hello
    }
}