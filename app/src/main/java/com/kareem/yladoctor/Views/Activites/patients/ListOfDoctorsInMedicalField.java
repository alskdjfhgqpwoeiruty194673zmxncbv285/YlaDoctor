package com.kareem.yladoctor.Views.Activites.patients;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.kareem.yladoctor.Factories.DatabasePathFactory;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Helpers.RecyclerAdapter;
import com.kareem.yladoctor.Models.Interfaces.RecyclerAdapterListener;
import com.kareem.yladoctor.Models.Modules.Fields.MedicalField;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.Views.ViewHolders.ListOfDoctorsInMedicalFieldViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kareem on 6/29/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class ListOfDoctorsInMedicalField extends AppCompatActivity implements RecyclerAdapterListener {
	@BindView(R.id.generalRecyclerViewListOfData_recyclerView_listOfItems)
	RecyclerView recyclerView;

	@Override
	protected void onCreate ( @Nullable Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.general_recyclerview_listofdata);
		ButterKnife.bind(this);
		new RecyclerAdapter(this).initRecyclerAdapterListener(null, recyclerView, MedicalField.class, ListOfDoctorsInMedicalFieldViewHolder.class, R.layout.doctor_in_medical_field_item, DatabasePathFactory.pathTo_MedicalFields_MedicalFieldUID((String) ((MainApplication)this.getApplication()).getUnkownObject()), FirebaseContracts.PATH_TO_MEDICALFIELDS);
	}

	@Override
	public void recyclerAdapterListener ( Object data, RecyclerView.ViewHolder viewHolder, Object holder, int position, String ID ) {
		if (ID.equals(FirebaseContracts.PATH_TO_MEDICALFIELDS)) {

		}
	}

	@Override
	public Context getMyContext () {
		return this;
	}
}
