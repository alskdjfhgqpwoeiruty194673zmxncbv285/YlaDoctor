package com.kareem.yladoctor.Factories;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Enums.WeekDays;
import com.kareem.yladoctor.Models.Helpers.FirebaseListener;
import com.kareem.yladoctor.Models.Helpers.SetValueListener;
import com.kareem.yladoctor.Models.Interfaces.FirebaseListeners;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.Schedule;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.ScheduleDay;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.ScheduleIdentifier;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Doctor;
import com.sdsmdg.tastytoast.TastyToast;

/**
 * Created by kareem on 7/2/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class ScheduleFactory implements FirebaseListeners {
	private Schedule schedule;
	private Context context;
	//	private ScheduleIdentifier identifier;
	private int hrs, mins;
	private String DUID;

	public ScheduleFactory ( Context context, String DUID ) {
		this.context = context;
		this.DUID = DUID;
	}

	public void bookAppointment ( Schedule schedule, final String dateOfToday, final String PUID ) {
		this.schedule = schedule;
		final MainApplication mainApplication = ((MainApplication) context.getApplicationContext());

		DatabasePathFactory.pathTo_Business_schedule_DayUID_TimeSlotUID(DUID, schedule.getDay(), schedule.getScheduleStartTime()).runTransaction(new Transaction.Handler() {
			@Override
			public Transaction.Result doTransaction ( MutableData mutableData ) {
				Schedule newS = mutableData.getValue(Schedule.class);
				if (newS == null) {
					TastyToast.makeText(context, "Appointment time slot doesn't exist", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
					Transaction.abort();
				}
				assert newS != null;
				if (newS.getCondition().equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE)) {
					String value;
					if (((Doctor) mainApplication.getOtherUser()).getCareer().isNeedsApproval())
						value = FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NEEDAPPROVAL;
					else
						value = FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_APPROVEDANDNEEDRELEASING;

					String ID = FirebaseDatabase.getInstance().getReference().push().getKey();
					newS.setCondition(value);
					newS.setID(ID);
					newS.setUID(PUID);
					createHistory(value);
					mutableData.setValue(newS);
					return Transaction.success(mutableData);
				}
				TastyToast.makeText(context, "This time is just booked already", TastyToast.LENGTH_LONG, TastyToast.ERROR);
				return Transaction.abort();

			}

			@Override
			public void onComplete ( DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot ) {
				if (databaseError == null)
					TastyToast.makeText(context, "Appointment is created", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
				else
					TastyToast.makeText(context, "Error occurred", TastyToast.LENGTH_LONG, TastyToast.ERROR);
			}
		});
	}

	public void approveAppointment ( Schedule schedule ) {
		this.schedule = schedule;
		final MainApplication mainApplication = ((MainApplication) context.getApplicationContext());

		DatabasePathFactory.pathTo_Business_schedule_DayUID_TimeSlotUID(mainApplication.getOtherUser().getUID(), schedule.getDay(), schedule.getScheduleStartTime()).runTransaction(new Transaction.Handler() {
			@Override
			public Transaction.Result doTransaction ( MutableData mutableData ) {
				Schedule newS = mutableData.getValue(Schedule.class);
				String value;
				String historyValue;
				assert newS != null;
				if (newS.getCondition().equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE)) {
					TastyToast.makeText(context, "No data for this Appointment time slot", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
					Transaction.abort();
				}
				if (newS.getCondition().equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NEEDAPPROVAL))
					historyValue = value = FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_APPROVEDANDNEEDRELEASING;
				else {
					value = FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE;
					historyValue = FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_DONE;
				}
				createHistory(historyValue);
				newS.setCondition(value);
				mutableData.setValue(newS);
				return Transaction.success(mutableData);
			}

			@Override
			public void onComplete ( DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot ) {
				if (databaseError == null)
					TastyToast.makeText(context, "Appointment is Modified", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
				else
					TastyToast.makeText(context, "Error occurred", TastyToast.LENGTH_LONG, TastyToast.ERROR);
			}
		});
	}

	private void createHistory ( String historyValue ) {
		// TODO: 7/3/2017 create historyIdentifier for both doctor and patient then create History
	}

	public void refuseAppointment () {
		createHistory(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOTAPPROVED);
	}

	public void patientCancelAppointment () {
		createHistory(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_CANCELLEDFROMPATIENT);
	}

	public void createNewSchedule ( WeekDays day ) {
		schedule = new Schedule();
		schedule.setDay(day);
		createTimePickerDialog("start", day);
	}

	private void createTimePickerDialog ( String titleWord, WeekDays day ) {
		TimePickerDialog dialog;
		dialog = new TimePickerDialog(context, timePickerListener(day), hrs, mins, false);
		dialog.setTitle("select " + titleWord + " time");
		dialog.show();
	}

	private TimePickerDialog.OnTimeSetListener timePickerListener ( final WeekDays day ) {
		return new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet ( TimePicker view, int hourOfDay, int minute ) {
				if (schedule.getScheduleStartTime() == null || schedule.getScheduleStartTime().isEmpty()) {
					schedule.setScheduleStartTime(hourOfDay + ":" + minute);
					createTimePickerDialog("end", day);
				} else {
					schedule.setScheduleEndTime(hourOfDay + ":" + minute);
					createScheduleOnDatabase(day);
				}
			}
		};
	}

	private void createScheduleOnDatabase ( WeekDays day ) {
		schedule.setID(FirebaseDatabase.getInstance().getReference().push().getKey());
		DatabasePathFactory.pathTo_Schedule_userUID_weekDay(DUID, day).runTransaction(new Transaction.Handler() {
			@Override
			public Transaction.Result doTransaction ( MutableData mutableData ) {
				ScheduleDay day = mutableData.getValue(ScheduleDay.class);
				if (day == null)
					day = new ScheduleDay(null, schedule.getDay());
				if (day.getName() == null)
					day.setName(schedule.getDay());
				day.addSchedule(schedule);
				mutableData.setValue(day);
				return Transaction.success(mutableData);
			}

			@Override
			public void onComplete ( DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot ) {
				if (databaseError == null)
					TastyToast.makeText(context, "Done", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
				else {
					TastyToast.makeText(context, "Failed" + databaseError.getMessage(), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
					Log.e("error with writing data", databaseError.getMessage());
				}
			}
		});
	}

	@Override
	public void ValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {

	}

	@Override
	public void SingleValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {

	}

	@Override
	public void onFailure ( Object data, Throwable error, String ID ) {
		TastyToast.makeText(context, "Failed", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
	}

	public void setDayOnHold ( WeekDays day ) {
		// TODO: 7/11/2017 set the code to loop on the schedule items and set it's values to ONHOLD
		// TODO: 7/11/2017 go to each booked user and convert the meeting status to canceled
		DatabasePathFactory.pathTo_Schedule_userUID_weekDay(DUID, day).runTransaction(new Transaction.Handler() {
			@Override
			public Transaction.Result doTransaction ( MutableData mutableData ) {
				ScheduleDay scheduleDay = mutableData.getValue(ScheduleDay.class);
				if (scheduleDay != null) {
					for (Schedule s : scheduleDay.getSchedules().values()) {
						if (! s.getCondition().equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_APPROVEDANDNEEDRELEASING) && ! s.getCondition().equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NEEDAPPROVAL))
							s.setCondition(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_ONHOLD);
						else {
							FirebaseDatabase.getInstance().getReference().child(FirebaseContracts.PATH_TO_APPOINTMENTHISTORYIDENTIFIER).runTransaction(new Transaction.Handler() {
								@Override
								public Transaction.Result doTransaction ( MutableData mutableData ) {
									return null;
								}

								@Override
								public void onComplete ( DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot ) {

								}
							});
						}
					}
					mutableData.setValue(scheduleDay);
					return Transaction.success(mutableData);
				} else
					return Transaction.abort();
			}

			@Override
			public void onComplete ( DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot ) {
				TastyToast.makeText(context, "Done", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
			}
		});
	}

	public void setDayActive ( WeekDays day ) {
		DatabasePathFactory.pathTo_Schedule_userUID_weekDay(DUID, day).runTransaction(new Transaction.Handler() {
			@Override
			public Transaction.Result doTransaction ( MutableData mutableData ) {
				ScheduleDay scheduleDay = mutableData.getValue(ScheduleDay.class);
				if (scheduleDay != null) {
					for (Schedule s : scheduleDay.getSchedules().values()) {
						s.setCondition(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE);
					}
					mutableData.setValue(scheduleDay);
					return Transaction.success(mutableData);
				} else
					return Transaction.abort();
			}

			@Override
			public void onComplete ( DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot ) {
				TastyToast.makeText(context, "Done", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
			}
		});
	}
}
