package org.agent.MedAgent.Mapper;

import org.agent.MedAgent.Object.Appointment;
import org.agent.MedAgent.Object.Archive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArchiveMapper {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Archive> getArchiveByidcard(String idcard){
        //按身份证查找
        Criteria criteria = Criteria.where("idcard").is(idcard);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Archive.class);
    }

}

