package org.agent.MedAgent.Service.Impl;
import org.agent.MedAgent.Mapper.AppointmentMapper;
import org.agent.MedAgent.Object.Appointment;
import org.agent.MedAgent.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.LambdaConversionException;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private AppointmentMapper appointmentMapper;

    /**
     * 查询满足任一条件的数据。，规则为：一个用户最多只能预约3次。一个用户不能重复预约同一个部门
     * @param appointment
     * @return
     */
    @Override
    public String bookAppointment(Appointment appointment) {
        List<Appointment> appointments = appointmentMapper.query(appointment);
        if(appointments.size()==3){
            return "一人最多只能预约3次！";
        }
        for(Appointment data : appointments) {
            if(data.getDepartment().equals(appointment.getDepartment()) && data.getDate().equals(appointment.getDate())){
                return "您在相同的科室和时间已有预约";
            }
        }
        appointmentMapper.save(appointment);
        return  "预约成功，并返回预约详情";
    }
}
