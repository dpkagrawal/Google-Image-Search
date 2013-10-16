package com.codepath.google.imagesearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.codepath.google.imagesearch.R;
import com.codepath.google.imagesearch.model.Image;
import com.loopj.android.image.SmartImageView;

public class ImageViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_view);
		SmartImageView imageView= (SmartImageView) findViewById(R.id.full_image);
		Intent i = getIntent();
		Image img = (Image) i.getSerializableExtra("results");
		imageView.setImageUrl(img.getFullUrl());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_view, menu);
		return true;
	}

}
