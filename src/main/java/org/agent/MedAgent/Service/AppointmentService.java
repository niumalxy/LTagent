package org.agent.MedAgent.Service;

import org.agent.MedAgent.Mapper.AppointmentMapper;
import org.agent.MedAgent.Object.Appointment;

public interface AppointmentService{
    Appointment getOne(Appointment appointment);
}
