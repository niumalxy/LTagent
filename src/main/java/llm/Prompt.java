package llm;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prompt {
    private String prompt = "";
    private ArrayList<String> format_list = new ArrayList<>();

    public Prompt(String text) {
        this.prompt = text;
        //获取prompt中需要被format的列表
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(this.prompt);
        while (matcher.find()) {
            this.format_list.add(matcher.group(1));
        }
    }

    public String Format(String... args){
        if(args.length%2 != 0){
            throw new IllegalArgumentException("Format need pairs of key-value.");
        }

        String temp = getPrompt();
        for(int i = 0; i< args.length; i += 2){
            String key = args[i];
            String value = args[i+1];
            temp = temp.replace('{'+key+'}', value);
        }
        return temp;
    }

    public String getPrompt(){
        return this.prompt;
    }
    public ArrayList<String> getFormatList(){
        return this.format_list;
    }
}
