package com.codepath.google.imagesearch.model;

import java.util.HashMap;
import java.util.Map;

import android.net.Uri;

public class Query {

	String path;
	HashMap<String, String> params;

	public Query(String path, HashMap<String, String> params) {
		this.path = path;
		this.params = new HashMap<String, String>();
		this.params.putAll(params);
	}

	public String toString() {
		StringBuffer parameterString = new StringBuffer();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			parameterString.append(entry.getKey() + "=" + Uri.encode(entry.getValue()) + "&");
		}
		return path + "?" + parameterString;
	}
}
