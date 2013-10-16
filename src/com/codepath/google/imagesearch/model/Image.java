package com.codepath.google.imagesearch.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R;

public class Image implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fullUrl;
	private String thumbUrl;

	public Image(JSONObject object) {
		try {
			this.fullUrl = object.getString("url");
			this.thumbUrl = object.getString("tbUrl");
		} catch (JSONException e) {
			this.fullUrl = null;
			this.thumbUrl = null;
		}
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public static ArrayList<Image> fromJSONArray(JSONArray imageArray) {
		ArrayList<Image> images = new ArrayList<Image>();
		for(int x=0; x < imageArray.length(); x++) {
			try {
				images.add(new Image(imageArray.getJSONObject(x)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return images;
	}
}
