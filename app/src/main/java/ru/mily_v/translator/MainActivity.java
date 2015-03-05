package ru.mily_v.translator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button startTranslateActivity = (Button) findViewById(R.id.main_button_translate);
		startTranslateActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, TranslateActivity.class);
				startActivity(i);
			}
		});

		final Button startDetermineActivity = (Button) findViewById(R.id.determine_button_determine);
		startDetermineActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, DetermineActivity.class);
				startActivity(i);
			}
		});

		final Button startAboutActivity = (Button) findViewById(R.id.main_button_about);
		startAboutActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, AboutActivity.class);
				startActivity(i);
			}
		});
	}
}
