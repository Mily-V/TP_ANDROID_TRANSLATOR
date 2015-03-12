package ru.mily_v.translator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * author s.titaevskiy on 12.03.15.
 */
public class DictionarySelectActivity extends Activity
		implements TranslateTask.TranslateTaskListener {

	public static String EXTRA_DICT = "extra_dict";
	private final List<String> dicts = new LinkedList<>();
	private ListView listView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dictionary_select);

		listView = ((ListView) findViewById(R.id.dictionary_select_list));
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent();
				i.putExtra(EXTRA_DICT, dicts.get(position));
				setResult(Activity.RESULT_OK, i);
				finish();
			}
		});
		new TranslateTask(this, "getLangs").execute();
	}

	@Override
	public void onPostExecute(JSONObject json) {
		findViewById(R.id.dictionary_select_progressbar).setVisibility(View.GONE);

		try {
			JSONArray dirs = json.getJSONArray("dirs");
			for (int i = 0; i < dirs.length(); ++i) {
				dicts.add(dirs.getString(i));
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}

		ArrayAdapter<String> adapter =
				new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, dicts);
		listView.setAdapter(adapter);
	}

	@Override
	public void onPreExecute() {
	}
}
