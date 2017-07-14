package com.kareem.yladoctor.Models.Modules.ScheduleModules;

import com.kareem.yladoctor.Models.Enums.WeekDays;

import java.util.ArrayList;

/**
 * Created by kareem on 7/2/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class ScheduleWeek {
	private ScheduleDay Saturday, Sunday, Monday, Tuesday, Wednesday, Thursday, Friday;

	public ScheduleWeek ( ScheduleDay saturday, ScheduleDay sunday, ScheduleDay monday, ScheduleDay tuesday, ScheduleDay wednesday, ScheduleDay thursday, ScheduleDay friday ) {
		Saturday = saturday;
		Sunday = sunday;
		Monday = monday;
		Tuesday = tuesday;
		Wednesday = wednesday;
		Thursday = thursday;
		Friday = friday;
	}

	public ScheduleDay[] listOfDays () {
		ArrayList<ScheduleDay> list = new ArrayList<>();
		if (Saturday != null)
			list.add(Saturday);

		if (Sunday != null)
			list.add(Sunday);

		if (Monday != null)
			list.add(Monday);

		if (Tuesday != null)
			list.add(Tuesday);

		if (Wednesday != null)
			list.add(Wednesday);

		if (Thursday != null)
			list.add(Thursday);

		if (Friday != null)
			list.add(Friday);
		return (ScheduleDay[]) list.toArray();
	}


	public ScheduleDay[] listOfAllDays () {
		ArrayList<ScheduleDay> list = new ArrayList<>();
		if (Saturday == null)
			Saturday = new ScheduleDay(null, WeekDays.Saturday);
		list.add(Saturday);

		if (Sunday == null)
			Sunday = new ScheduleDay(null, WeekDays.Sunday);
		list.add(Sunday);

		if (Monday == null)
			Monday = new ScheduleDay(null, WeekDays.Monday);
		list.add(Monday);

		if (Tuesday == null)
			Tuesday = new ScheduleDay(null, WeekDays.Tuesday);
		list.add(Tuesday);

		if (Wednesday == null)
			Wednesday = new ScheduleDay(null, WeekDays.Wednesday);
		list.add(Wednesday);

		if (Thursday == null)
			Thursday = new ScheduleDay(null, WeekDays.Thursday);
		list.add(Thursday);

		if (Friday == null)
			Friday = new ScheduleDay(null, WeekDays.Friday);
		list.add(Friday);

		return list.toArray(new ScheduleDay[list.size()]);
	}

	public ScheduleWeek () {
	}

	public ScheduleDay getSaturday () {
		return Saturday;
	}

	public void setSaturday ( ScheduleDay saturday ) {
		Saturday = saturday;
	}

	public ScheduleDay getSunday () {
		return Sunday;
	}

	public void setSunday ( ScheduleDay sunday ) {
		Sunday = sunday;
	}

	public ScheduleDay getMonday () {
		return Monday;
	}

	public void setMonday ( ScheduleDay monday ) {
		Monday = monday;
	}

	public ScheduleDay getTuesday () {
		return Tuesday;
	}

	public void setTuesday ( ScheduleDay tuesday ) {
		Tuesday = tuesday;
	}

	public ScheduleDay getWednesday () {
		return Wednesday;
	}

	public void setWednesday ( ScheduleDay wednesday ) {
		Wednesday = wednesday;
	}

	public ScheduleDay getThursday () {
		return Thursday;
	}

	public void setThursday ( ScheduleDay thursday ) {
		Thursday = thursday;
	}

	public ScheduleDay getFriday () {
		return Friday;
	}

	public void setFriday ( ScheduleDay friday ) {
		Friday = friday;
	}
}
