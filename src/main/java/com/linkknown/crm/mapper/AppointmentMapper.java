package com.linkknown.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkknown.crm.bean.dos.Appointment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoupeng
 */
@Repository
public interface AppointmentMapper extends BaseMapper<Appointment> {

    public Appointment selectAppointmentById(Integer appointmentId);

    public List<Appointment> selectAppointmentList(Appointment appointment);

    public int insertAppointment(Appointment appointment);

    public int updateAppointment(Appointment appointment);

    public int deleteAppointmentById(Integer appointmentId);

    public int deleteAppointmentByIds(String[] appointmentIds);
}
