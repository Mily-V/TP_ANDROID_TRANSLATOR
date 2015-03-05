package ru.mily_v.translator;

import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

import ru.mily_v.translator.fragments.AuthorsFragment;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button startTranslateActivity = (Button) findViewById(R.id.main_button_for_translate);
		startTranslateActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, TranslateActivity.class);
				startActivity(i);
			}
		});

		final Button startDetermineActivity = (Button) findViewById(R.id.main_button_for_determine);
		startDetermineActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, DetermineActivity.class);
				startActivity(i);
			}
		});

        final Button startAuthorsActivity = (Button) findViewById(R.id.main_button_authors);
        startAuthorsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AuthorsFragment authorsNameFragment = new AuthorsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.main_fragment_authors, authorsNameFragment);
                transaction.commit();
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
