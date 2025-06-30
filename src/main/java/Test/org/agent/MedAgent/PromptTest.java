package Test.org.agent.MedAgent;

import org.agent.MedAgent.llm.Prompt;
import org.agent.MedAgent.llm.llm;

public class PromptTest {
    public static void main(String[] args) {
        String template = "把以下文本内容翻译成{style}的风格。\n" +
                "文本：{text}";
        Prompt prompt = new Prompt(template);
        String chat_text = prompt.Format("style", "666", "text", "武帝");
        System.out.println(llm.chat(chat_text));
    }

}
