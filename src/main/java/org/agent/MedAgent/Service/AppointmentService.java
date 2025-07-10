package org.agent.MedAgent.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.agent.MedAgent.Mapper.AppointmentMapper;
import org.agent.MedAgent.Object.Appointment;

public interface AppointmentService{
    Appointment getOne(Appointment appointment);
}
