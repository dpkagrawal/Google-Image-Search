package com.codepath.google.imagesearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;

import com.codepath.google.imagesearch.R;
import com.codepath.google.imagesearch.model.Image;
import com.loopj.android.image.SmartImageView;

public class ImageViewActivity extends Activity {
	private Image img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_view);
		SmartImageView imageView = (SmartImageView) findViewById(R.id.full_image);

		Intent i = getIntent();
		img = (Image) i.getSerializableExtra("results");
		imageView.setImageUrl(img.getFullUrl());
		imageView.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				Intent shareIntent = new Intent();
				shareIntent.setAction(Intent.ACTION_SEND);
				shareIntent.putExtra(Intent.EXTRA_STREAM, img.getFullUrl());
				shareIntent.setType("image/jpeg");
				startActivity(Intent.createChooser(shareIntent, Html.fromHtml(img.getTitle())));
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_view, menu);
		return true;
	}

}
