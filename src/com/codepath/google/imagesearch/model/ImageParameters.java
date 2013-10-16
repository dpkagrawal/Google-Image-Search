package com.codepath.google.imagesearch.model;

import java.io.Serializable;
import java.util.HashMap;

public class ImageParameters implements Serializable {

	private static final long serialVersionUID = 1L;

	private String query;
	private String imageSize;
	private String colorFilter;
	private String imageType;
	private String site;
	private int start = 0;
	private int limit = 8;

	public ImageParameters() {
	}

	public ImageParameters(String imageSize, String colorFilter,
			String imageType, String site) {
		this.imageSize = imageSize;
		this.colorFilter = colorFilter;
		this.imageType = imageType;
		this.site = site;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getImageSize() {
		return imageSize;
	}

	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}

	public String getColorFilter() {
		return colorFilter;
	}

	public void setColorFilter(String colorFilter) {
		this.colorFilter = colorFilter;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public HashMap<String, String> toHash() {
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("q", this.getQuery());
		if (this.getImageSize() != null)
			params.put("imgsz", this.getImageSize());

		if (this.getColorFilter() != null)
			params.put("imgcolor", this.getColorFilter());

		if (this.getImageType() != null)
			params.put("imgtype", this.getImageType());

		if (this.getSite() != null)
			params.put("as_sitesearch", this.getSite());

		params.put("start", Integer.toString(this.getStart()));
		params.put("end", Integer.toString(this.getLimit()));
		params.put("v", "1.0");
		return params;
	}

}
