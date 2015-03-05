package ru.mily_v.translator;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * author s.titaevskiy on 05.03.15.
 */
public class TranslateTask extends AsyncTask<String, Void, String> {

	private final static String key = "trnsl.1.1.20150305T113444Z.109d980204fe813f.f5575b41e5e7f6681338678e1fb6be394baa5093";
	private String url = "https://translate.yandex.net/api/v1.5/tr.json/";
	private TranslateTaskListener callback;

	public TranslateTask(TranslateTaskListener callback, String task) {
		this.callback = callback;
		url += task;
	}

	@Override
	protected String doInBackground(String... params) {
		List<NameValuePair> getParams = new LinkedList<>();

		getParams.add(new BasicNameValuePair("key", key));
		if (params.length >= 1) {
			getParams.add(new BasicNameValuePair("text", params[0]));
		}
		if (params.length >= 2) {
			getParams.add(new BasicNameValuePair("lang", params[1]));
		}

		url += '?';
		url += URLEncodedUtils.format(getParams, "utf-8");

		return doRequest();
	}

	private String doRequest() {
		String response = "";
		response = httpRequest(response);
		response = parseJSONResponse(response);
		return response;
	}

	private String httpRequest(String response) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			response = EntityUtils.toString(httpClient.execute(httpGet).getEntity());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private String parseJSONResponse(String response) {
		try {
			response = new JSONObject(response).getJSONArray("text").getString(0);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		callback.onPreExecute();
	}

	@Override
	protected void onPostExecute(String s) {
		super.onPostExecute(s);
		callback.onPostExecute(s);
	}

	public interface TranslateTaskListener {
		public void onPostExecute(String s);

		public void onPreExecute();
	}
}
