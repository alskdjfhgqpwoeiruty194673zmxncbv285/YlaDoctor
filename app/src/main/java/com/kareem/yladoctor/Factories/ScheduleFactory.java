package com.kareem.yladoctor.Factories;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Interfaces.FirebaseListeners;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.Schedule;
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

	public ScheduleFactory ( Schedule schedule, Context context ) {
		this.context = context;
		this.schedule = schedule;
	}

	public void bookAppointment ( final String dateOfToday ) {
		final MainApplication mainApplication = ((MainApplication) context.getApplicationContext());

		DatabasePathFactory.pathTo_Business_schedule_DayUID_TimeSlotUID(mainApplication.getOtherUser().getUID(), schedule.getDay(), schedule.getTime()).runTransaction(new Transaction.Handler() {
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
					if (((Doctor) mainApplication.getOtherUser()).getDoctorCareer().isNeedsApproval())
						value = FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NEEDAPPROVAL;
					else
						value = FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_APPROVEDANDNEEDRELEASING;

					createHistory(value);
					mutableData.setValue(new Schedule(dateOfToday, mainApplication.getUser().getUID(), schedule.getTime(), value, schedule.getDay()));
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

	public void approveAppointment () {
		final MainApplication mainApplication = ((MainApplication) context.getApplicationContext());

		DatabasePathFactory.pathTo_Business_schedule_DayUID_TimeSlotUID(mainApplication.getOtherUser().getUID(), schedule.getDay(), schedule.getTime()).runTransaction(new Transaction.Handler() {
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

	public void cancelAppointment () {
		createHistory(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_CANCELLED);
	}

	@Override
	public void ValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {

	}

	@Override
	public void SingleValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {

	}

	@Override
	public void onFailure ( Object data, Throwable error, String ID ) {

	}
}
