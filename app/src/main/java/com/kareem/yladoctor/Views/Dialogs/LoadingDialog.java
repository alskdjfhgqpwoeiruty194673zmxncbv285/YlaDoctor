/*
 * Copyright (c) $today.year.kareem elsayed aly,no one has the authority to edit,delete,copy any part without my permission
 */

package com.kareem.yladoctor.Views.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.kareem.yladoctor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kareem on 9/8/2016 - HealMe.
 * <br></br>
 * To load a loading logo as a waiting sign
 *
 * @author kareem
 * @version %I%
 */

public class LoadingDialog extends Dialog {

    @BindView(R.id.generalLoadingLogo_imageView_logoImage)
    ImageView imageView;

    public LoadingDialog(Context context) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.general_loading_logo);

        //noinspection ConstantConditions
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ButterKnife.bind(this);
        imageView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate_logo));
    }
}
