package com.codepath.google.imagesearch.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.codepath.google.imagesearch.R;
import com.codepath.google.imagesearch.abstractclass.EndlessScrollListener;
import com.codepath.google.imagesearch.adapter.ImageResultArrayAdapter;
import com.codepath.google.imagesearch.model.Image;
import com.codepath.google.imagesearch.model.ImageParameters;
import com.codepath.google.imagesearch.model.Query;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ImageSearchActivity extends Activity {

	private static String GOOGLE_SEARCH_HOST = "https://ajax.googleapis.com/ajax/services/search/images";
	EditText etSearchTerm;
	GridView gvImageView;
	Query query;
	ArrayList<Image> images = new ArrayList<Image>();
	ImageResultArrayAdapter imageAdapter;
	ImageParameters imgParams = new ImageParameters();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, images);
		gvImageView.setAdapter(imageAdapter);
		setupOnGridItemClickListener();
		setupOnScrollListner();
	}

	private void setupOnScrollListner() {
		gvImageView.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				imgParams.setStart(page);
				getImagesFromGoogle();
			}
		});
	}

	public void setupOnGridItemClickListener() {
		gvImageView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent,
					int position, long arg3) {
				Intent i = new Intent(getApplicationContext(),
						ImageViewActivity.class);
				Image img = (Image) images.get(position);
				i.putExtra("results", img);
				startActivity(i);
			}
		});
	}

	public void setupViews() {
		etSearchTerm = (EditText) findViewById(R.id.etSearchTerm);
		gvImageView = (GridView) findViewById(R.id.gvImageView);
	}

	public void onImageSearch(View view) {
		String searchTerm = etSearchTerm.getText().toString();
		imgParams.setQuery(searchTerm);

		Toast.makeText(this, "Searching for " + searchTerm, Toast.LENGTH_SHORT)
				.show();

		getImagesFromGoogle();
	}

	public void getImagesFromGoogle() {
		query = new Query(GOOGLE_SEARCH_HOST, imgParams.toHash());
		AsyncHttpClient client = new AsyncHttpClient();

		client.get(query.toString(), new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONObject response) {
				JSONArray imageArray = null;
				try {
					imageArray = response.getJSONObject("responseData")
							.getJSONArray("results");
					imageAdapter.addAll(Image.fromJSONArray(imageArray));

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.image_search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			onClickAdvanceSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onClickAdvanceSettings() {
		Intent i = new Intent(getApplicationContext(),
				AdvanceSettingActivity.class);
		startActivityForResult(i, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				imgParams = (ImageParameters) data
						.getSerializableExtra("params");
			}
		}
	}
}