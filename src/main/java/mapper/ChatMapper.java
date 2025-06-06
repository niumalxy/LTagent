package mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public class ChatMapper {

    @Update("")
    public void SaveChat(String chat_history){

    }
}
