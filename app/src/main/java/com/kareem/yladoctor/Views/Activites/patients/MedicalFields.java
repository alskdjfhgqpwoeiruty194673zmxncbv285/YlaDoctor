package com.kareem.yladoctor.Views.Activites.patients;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Helpers.RecyclerAdapter;
import com.kareem.yladoctor.Models.Interfaces.RecyclerAdapterListener;
import com.kareem.yladoctor.Models.Modules.Fields.MedicalFieldIdentifier;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.Views.ViewHolders.MedicalFieldIdentifierViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kareem on 6/17/2017 - YlaCreateContent.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class MedicalFields extends AppCompatActivity implements RecyclerAdapterListener, MedicalFieldIdentifierViewHolder.MedicalFieldIdentifierViewHolderInterface {
	@BindView(R.id.generalRecyclerViewListOfData_recyclerView_listOfItems)
	RecyclerView recyclerView;

	@Override
	protected void onCreate ( @Nullable Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.general_recyclerview_listofdata);
		ButterKnife.bind(this);
		RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this);
		try {
			recyclerAdapter.initRecyclerAdapterListener(null, recyclerView, MedicalFieldIdentifier.class, MedicalFieldIdentifierViewHolder.class, R.layout.patient_medical_field_item, getPathOfMedicalFields(), FirebaseContracts.PATH_TO_MEDICALFIELDSIDENTIFIER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void recyclerAdapterListener ( Object data, RecyclerView.ViewHolder viewHolder, Object holder, int position, String ID ) {
		MedicalFieldIdentifier medicalFieldIdentifier = (MedicalFieldIdentifier) holder;
		MedicalFieldIdentifierViewHolder medicalFieldIdentifierViewHolder = (MedicalFieldIdentifierViewHolder) viewHolder;
		medicalFieldIdentifierViewHolder.setDataToGUI(medicalFieldIdentifier, this);
	}

	@Override
	public Context getMyContext () {
		return this;
	}

	private DatabaseReference getPathOfMedicalFields () {
		return FirebaseDatabase.getInstance().getReference().child(FirebaseContracts.PATH_TO_MEDICALFIELDSIDENTIFIER);
	}

	@Override
	public void onMedicalFieldIsSelected ( String UID ) {
		((MainApplication) this.getApplication()).setUnkownObject(UID);
		startActivity(new Intent(this, ListOfDoctorsInMedicalField.class));
	}
}
