package org.agent.MedAgent.Mapper;

import org.agent.MedAgent.Object.ChatItem;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ChatMapper extends MongoRepository<ChatItem, String> {


}
