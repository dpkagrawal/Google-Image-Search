package com.codepath.google.imagesearch.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.codepath.google.imagesearch.R;
import com.codepath.google.imagesearch.model.Image;
import com.loopj.android.image.SmartImageView;

public class ImageResultArrayAdapter extends ArrayAdapter<Image> {

	public ImageResultArrayAdapter(Context context, ArrayList<Image> images) {
		super(context, R.layout.image_view_result, images);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Image image = this.getItem(position);
		SmartImageView smartImageView;

		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			smartImageView = (SmartImageView) inflator.inflate(R.layout.image_view_result,parent, false);
		} else {
			smartImageView = (SmartImageView) convertView;
			smartImageView.setImageResource(android.R.color.transparent);
		}
		smartImageView.setImageUrl(image.getThumbUrl());
		return smartImageView;
	}
}
