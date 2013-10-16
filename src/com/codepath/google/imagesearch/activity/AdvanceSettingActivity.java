package com.codepath.google.imagesearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.codepath.google.imagesearch.R;
import com.codepath.google.imagesearch.model.ImageParameters;

public class AdvanceSettingActivity extends Activity {

	Spinner imageSize;
	Spinner colorFilter;
	Spinner imageType;
	EditText site;
	Button save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advance_setting);
		setupViews();

	}

	public void setupViews() {
		imageSize = (Spinner) findViewById(R.id.imageSizeValue);
		colorFilter = (Spinner) findViewById(R.id.colorFilterValue);
		imageType = (Spinner) findViewById(R.id.imageTypeValue);
		site = (EditText) findViewById(R.id.siteValue);
		save = (Button) findViewById(R.id.saveButton);
	}

	public void setupReturnIntent(View view) {
		Intent returnIntent = new Intent();
		
		ImageParameters imgParam = new ImageParameters();
		imgParam.setImageSize(imageSize.getSelectedItem().toString());
		imgParam.setColorFilter(colorFilter.getSelectedItem().toString());
		imgParam.setImageType(imageType.getSelectedItem().toString());
		imgParam.setSite(site.getText().toString());
		
		returnIntent.putExtra("params", imgParam);
		setResult(RESULT_OK, returnIntent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.advance_setting, menu);
		return true;
	}

}
