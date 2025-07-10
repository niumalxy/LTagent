package org.agent.MedAgent.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.agent.MedAgent.Mapper.AppointmentMapper;
import org.agent.MedAgent.Object.Appointment;
import org.agent.MedAgent.Service.AppointmentService;
import org.springframework.stereotype.Service;

import java.lang.invoke.LambdaConversionException;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {
    /**
     * 查询满足任一条件的数据
     * @param appointment
     * @return
     */
    @Override
    public Appointment getOne(Appointment appointment) {
        //返回一条满足条件的即可
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getUsername, appointment.getUsername());
        queryWrapper.eq(Appointment::getIdCard, appointment.getIdCard());
        queryWrapper.eq(Appointment::getDepartment, appointment.getDepartment());
        queryWrapper.eq(Appointment::getDate, appointment.getDate());
        queryWrapper.eq(Appointment::getTime, appointment.getTime());
        return baseMapper.selectOne(queryWrapper);
    }
}
