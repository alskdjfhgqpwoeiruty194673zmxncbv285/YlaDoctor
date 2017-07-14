package com.kareem.yladoctor.Models.Modules.ScheduleModules;

import com.kareem.yladoctor.Models.Enums.WeekDays;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kareem on 7/2/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class ScheduleDay {
	private HashMap<String, Schedule> schedules;
	private WeekDays name;
	private boolean active=true;

	public ScheduleDay ( HashMap<String, Schedule> schedules, WeekDays name ) {
		this.schedules = schedules;
		this.name = name;
	}

	public ScheduleDay () {
	}

	public Schedule[] listOfSchedules () {
		if (schedules != null) {
			ArrayList<Schedule> list = new ArrayList<>();
			for (Schedule s : schedules.values()) {
				if (s != null)
					list.add(s);
			}
			return (Schedule[]) list.toArray(new Schedule[list.size()]);
		}
		return null;
	}

	public HashMap<String, Schedule> getSchedules () {
		return schedules;
	}

	public void setSchedules ( HashMap<String, Schedule> schedules ) {
		this.schedules = schedules;
	}

	public WeekDays getName () {
		return name;
	}

	public void setName ( WeekDays name ) {
		this.name = name;
	}

	public boolean isActive () {
		return active;
	}

	public void setActive ( boolean active ) {
		this.active = active;
	}
	public void addSchedule(Schedule schedule){
		if (schedules==null)
			schedules=new HashMap<>();
		schedules.put(schedule.getID(),schedule);
	}
}
