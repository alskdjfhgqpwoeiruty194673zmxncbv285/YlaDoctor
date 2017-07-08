package com.kareem.yladoctor.Models.Contracts;

import com.kareem.yladoctor.Models.Enums.DefaultLanguages;

/**
 * Created by kareem on 5/28/2017 - Akshef.
 * <br></br>
 * firebase database relations
 *
 * @author kareem
 * @version %I%
 */

public class FirebaseContracts {

	public static final String PATH_TO_USERS = "users";
	public static final String PATH_TO_USERS_USERUID_ACCOUNTTYPE = "accountType";
	public static final String PATH_TO_USERS_USERUID_EMAIL = "email";
	public static final String PATH_TO_USERS_USERUID_UID = "uid";
	public static final String PATH_TO_USERS_USERUID_PASSWORD = "password";

	public static final String PATH_TO_USERS_INDIVIDUALUID_POINTS = "points";
	public static final String PATH_TO_USERS_INDIVIDUALUID_MOBILENUMBER = "mobileNumber";
	public static final String PATH_TO_USERS_INDIVIDUALUID_NAME = "name";
	public static final String PATH_TO_USERS_INDIVIDUALUID_PROFILEPICTURE = "profilePicture";
	public static final String PATH_TO_USERS_INDIVIDUALUID_PROFILEPICTURE_URL = "URL";
	public static final String PATH_TO_USERS_INDIVIDUALUID_PROFILEPICTURE_ATTACHMENTTYPE = "attachmentType";
	public static final String PATH_TO_USERS_INDIVIDUALUID_PROFILEPICTURE_ATTACHMENTNAME = "attachmentName";
	public static final String PATH_TO_USERS_INDIVIDUALUID_PROFILEPICTURE_ATTACHMENTSIZE = "attachmentSize";

	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_POINTS = "points";
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_MOBILENUMBER = "mobileNumber";
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_NAMES = "names";
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_NAMES_ARABIC = String.valueOf(DefaultLanguages.Arabic);
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_NAMES_ENGLISH = String.valueOf(DefaultLanguages.English);
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_PROFILEPICTURE = "profilePicture";
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_PROFILEPICTURE_URL = "URL";
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_PROFILEPICTURE_ATTACHMENTTYPE = "attachmentType";
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_PROFILEPICTURE_ATTACHMENTNAME = "attachmentName";
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_PROFILEPICTURE_ATTACHMENTSIZE = "attachmentSize";

	public static final String PATH_TO_USERS_PATIENTSUID_EMERGENCYMOBILENUMBER = "emergencyMobileNumber";

	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER = "career";
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_BUSINESSLOCATIONS = "businessLocations";
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_BUSINESSMOBILENUMBER = "businessMobileNumber";
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_RATE = "rate";
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_VOTES = "votes";
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_NEEDSAPPROVAL = "needsApproval";
	public static final String PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_ACTIVATEDDOCTOR = "activatedDoctor";
	public static final String PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_EXPERIENCEYEARS = "experienceYears";
	public static final String PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_PRICE = "price";
	public static final String PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_INTERVAL = "interval";
	public static final String PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_FIELDID = "fieldID";
	public static final String PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_LISTOFHEALTHINSURANCECOMPANIES
			  = "listOfHealthInsuranceCompanies";

	//used only for the career factories as communication between factories
	public static final String PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_ITEM= "item";


	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER = "MedicalFieldsIdentifier";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_NAMES = "names";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_NAMES_ARABIC = "Arabic";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_NAMES_ENGLISH = "English";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_DESCRIPTIONS_ENGLISH = "English";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_DESCRIPTIONS_ARABIC = "Arabic";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_UID = "UID";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_NUMBEROFDOCTORS = "numberOfDoctors";

	public static final String PATH_TO_MEDICALFIELDS = "MedicalFields";
	public static final String PATH_TO_MEDICALFIELDS_MEDICALFIELDSID_UID = "UID";
	public static final String PATH_TO_MEDICALFIELDS_MEDICALFIELDSID_DOCTORS = "doctors";
	public static final String PATH_TO_MEDICALFIELDS_MEDICALFIELDSID_DOCTORS_DOCTORSUID_UID = "UID";

	public static final String PATH_TO_HEALTHINSURANCECOMPANIES = "healthInsuranceCompanies";
	public static final String PATH_TO_HEALTHINSURANCECOMPANIES_HEALTHINSURANCECOMPANIESID_UID = "UID";
	public static final String PATH_TO_HEALTHINSURANCECOMPANIES_HEALTHINSURANCECOMPANIESID_NAMES_ARABIC = "Arabic";
	public static final String PATH_TO_HEALTHINSURANCECOMPANIES_HEALTHINSURANCECOMPANIESID_NAMES_ENGLISH = "English";

	public static final String PATH_TO_APPOINTMENTHISTORYIDENTIFIER = "appointmentHistoryIdentifier";
	public static final String PATH_TO_APPOINTMENTHISTORYIDENTIFIER_USERUID_SAVEDID_ID = "id";
	public static final String PATH_TO_APPOINTMENTHISTORYIDENTIFIER_USERUID_SAVEDID_UID = "uid";

	public static final String PATH_TO_APPOINTMENTHISTORY = "appointmentHistory";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_DATE = "date";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_DUID = "duid";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_FIELD = "field";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_PUID = "puid";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_PATIENTREVIEW = "PReview";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_DOCTORREVIEW = "DReview";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_TIME = "time";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_VOTE = "vote";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_REVIEWOWNER_RATE = "rate";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_REVIEWOWNER_DESC = "desc";

	public static final String PATH_TO_SCHEDULEIDENTIFIER = "scheduleIdentifier";
	public static final String PATH_TO_SCHEDULEIDENTIFIER_USERSUID_DAYUID_GENERATEDUID_UID = "UID";
	public static final String PATH_TO_SCHEDULEIDENTIFIER_USERSUID_DAYUID_GENERATEDUID_start = "start";
	public static final String PATH_TO_SCHEDULEIDENTIFIER_USERSUID_DAYUID_GENERATEDUID_END = "end";


	public static final String PATH_TO_SCHEDULE = "schedule";
	public static final String PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION = "condition";
	public static final String PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE = "noValue";
	public static final String PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_DONE = "done";
	public static final String PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_CANCELLED = "cancelled";
	public static final String PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NEEDAPPROVAL = "needApproval";
	public static final String PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOTAPPROVED = "notApproved";
	public static final String PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_APPROVEDANDNEEDRELEASING = "approvedAndNeedReleasing";
	public static final String PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_DATE = "date";
	public static final String PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_TIME = "time";
	public static final String PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_UID = "uid";

	public static final String ARABIC = String.valueOf(DefaultLanguages.Arabic);
	public static final String ENGLISH = String.valueOf(DefaultLanguages.English);
	public static final String PATH_TO_GEOFIRELOCATION = "geoFireLocation";

	public static final String STORAGE_PATH_TO_IMAGES = "images";
	public static final String STORAGE_PATH_TO_IMAGES_PERSONAL = "personal";
	public static final String STORAGE_PATH_TO_IMAGES_CHAT = "chat";

	public static final String STORAGE_PATH_TO_FILES = "files";
	public static final String STORAGE_PATH_TO_FILES_PERSONAL = "personal";
	public static final String STORAGE_PATH_TO_FILES_CHAT = "chat";

	public static final String PASSWORD = "yladoctor";
}
