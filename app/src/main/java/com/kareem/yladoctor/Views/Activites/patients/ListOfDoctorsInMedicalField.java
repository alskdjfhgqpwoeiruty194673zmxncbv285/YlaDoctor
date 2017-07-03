package com.kareem.yladoctor.Views.Activites.patients;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kareem.yladoctor.R;

/**
 * Created by kareem on 6/29/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class ListOfDoctorsInMedicalField extends AppCompatActivity {
	@Override
	protected void onCreate ( @Nullable Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.general_recyclerview_listofdata);
	}
}
