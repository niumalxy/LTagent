package org.agent.MedAgent.Mapper;

import org.agent.MedAgent.Object.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class AppointmentMapper{
    @Autowired
    private MongoTemplate mongoTemplate;

    //从mongodb中找预约
    public List<Appointment> query(Appointment appointment){
        //按身份证查找
        Criteria criteria = Criteria.where("idCard").is(appointment.getIdCard());
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Appointment.class);
    }

    public void save(Appointment appointment){
        mongoTemplate.save(appointment);
    }

    public void deleteById(Long id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, "Appointment");
    }
}
