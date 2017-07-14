package com.kareem.yladoctor.Factories;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Enums.DefaultLanguages;
import com.kareem.yladoctor.Models.Enums.WeekDays;

/**
 * Created by kareem on 6/28/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class DatabasePathFactory {
	private static DatabaseReference getReference () {
		return FirebaseDatabase.getInstance().getReference();
	}

	public static DatabaseReference pathTo_User_UserUID ( String UID ) {
		return getReference().child(FirebaseContracts.PATH_TO_USERS).child(UID);
	}

	public static DatabaseReference pathTo_User_UserUID_AccountType ( String UID ) {
		return pathTo_User_UserUID(UID).child(FirebaseContracts.PATH_TO_USERS_USERUID_ACCOUNTTYPE);
	}

	public static DatabaseReference pathTo_Business_schedule ( String UID ) {
		return getReference().child(FirebaseContracts.PATH_TO_SCHEDULEIDENTIFIER).child(UID);
	}

	public static DatabaseReference pathTo_Business_schedule_DayUID_TimeSlotUID ( String doctorUID, WeekDays day, String timeSlot ) {
		return getReference().child(FirebaseContracts.PATH_TO_SCHEDULE).child(doctorUID).child(day.toString()).child(timeSlot);
	}

	public static DatabaseReference pathTo_GeoFireLocation () {
		return getReference().child(FirebaseContracts.PATH_TO_GEOFIRELOCATION);
	}

	public static DatabaseReference pathTo_MedicalFieldIdentifier_FieldUID_Name_Language ( String MedicaFieldUID, String defaultLanguages ) {
		return getReference().child(FirebaseContracts.PATH_TO_MEDICALFIELDSIDENTIFIER).child(MedicaFieldUID).child(FirebaseContracts.PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_NAMES).child(defaultLanguages);
	}

	//	public static DatabaseReference pathTo_ScheduleIdentifier_userUID_weekDay ( String UID, WeekDays weekDay ) {
//		return getReference().child(FirebaseContracts.PATH_TO_SCHEDULEIDENTIFIER + "/" + UID + "/" + weekDay.toString());
//
//	}
	public static DatabaseReference pathTo_Schedule_userUID_weekDay ( String UID, WeekDays weekDay ) {
		return getReference().child(FirebaseContracts.PATH_TO_SCHEDULE + "/" + UID + "/" + weekDay.toString());

	}

	public static DatabaseReference pathTo_MedicalFields_MedicalFieldUID ( String FieldUID ) {
		return getReference().child(FirebaseContracts.PATH_TO_MEDICALFIELDS + "/" + FieldUID);

	}
}
