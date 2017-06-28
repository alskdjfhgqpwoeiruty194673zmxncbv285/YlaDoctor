package com.kareem.yladoctor.Views.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Modules.Fields.MedicalFieldIdentifier;
import com.kareem.yladoctor.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kareem on 6/23/2017 - YlaCreateContent.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class MedicalFieldIdentifierViewHolder extends RecyclerView.ViewHolder {
	private MedicalFieldIdentifierViewHolderInterface medicalFieldIdentifierViewHolderInterface;
	private MedicalFieldIdentifier medicalFieldIdentifier;
	@BindView(R.id.doctorMedicalFieldItem_textView_name)
	public TextView name;
	@BindView(R.id.doctorMedicalFieldItem_textView_description)
	public TextView description;
	@BindView(R.id.doctorMedicalFieldItem_textView_numberOfDoctors)
	public TextView numberOfDoctors;
	@BindView(R.id.doctorMedicalFieldItem_imageView_image)
	public ImageView icon;

	@OnClick(R.id.doctorMedicalFieldItem_linearLayout_container)
	public void onTotalItemClicked () {
medicalFieldIdentifierViewHolderInterface.onMedicalFieldIsSelected(medicalFieldIdentifier.getUID());
	}

	public MedicalFieldIdentifierViewHolder ( View itemView ) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void setDataToGUI ( MedicalFieldIdentifier medicalFieldIdentifier, MedicalFieldIdentifierViewHolderInterface medicalFieldIdentifierViewHolderInterface ) {
		this.medicalFieldIdentifierViewHolderInterface = medicalFieldIdentifierViewHolderInterface;
		this.medicalFieldIdentifier=medicalFieldIdentifier;
		name.setText(medicalFieldIdentifier.getNames().get(FirebaseContracts.ARABIC));
		description.setText(medicalFieldIdentifier.getDescriptions().get(FirebaseContracts.ARABIC));
		numberOfDoctors.setText(medicalFieldIdentifier.getNumberOfDoctors() + "");
//		if (!medicalFieldIdentifier.getURL().isEmpty()&&!medicalFieldIdentifier.getURL().equals(""))
//		Glide.with(medicalFieldIdentifierViewHolderInterface.getMyContext()).load(medicalFieldIdentifier.getURL()).into(icon);
	}

	public interface MedicalFieldIdentifierViewHolderInterface {
		public Context getMyContext ();

		public void onMedicalFieldIsSelected ( String UID );
	}
}
