package fediverse.writefreely.ekrilib;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.yydcdut.markdown.MarkdownConfiguration.Builder;
import com.yydcdut.markdown.MarkdownEditText;
import com.yydcdut.markdown.MarkdownProcessor;
import com.yydcdut.markdown.syntax.edit.EditFactory;
import fediverse.writefreely.api.model.Collection;
import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_main);

		MarkdownProcessor markdownProcessor = new MarkdownProcessor(this);
		markdownProcessor.config(new Builder(this).build());
		markdownProcessor.factory(EditFactory.create());
		markdownProcessor.live((MarkdownEditText) findViewById(R.id.plain_text_input));

		super.setSupportActionBar(super.findViewById(R.id.main_toolbar));
		super.getSupportActionBar().setDisplayShowTitleEnabled(false);
		super.findViewById(R.id.main_view_blogs)
		     .setOnClickListener((final View v) -> startActivity(new Intent(MainActivity.this,
		                                                                    BlogsActivity.class)));

		new MyTask().execute((Spinner) super.findViewById(R.id.main_spinner_nav));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.getMenuInflater().inflate(R.menu.menu_main, menu);

		return true;
	}

	private class MyTask extends AsyncTask<Spinner, Void, Pair<Spinner,
	                                                           Collection[]>> {
		@Override
		protected Pair<Spinner, Collection[]> doInBackground(Spinner... params) {
			try {
				return Pair.create(params[0],
				                   LoginActivity.wf.retrieveUserCollections().getData());
			} catch(final IOException e) {
				return Pair.create(params[0], new Collection[] {});
			}
		}

		@Override
		protected void onPostExecute(final Pair<Spinner, Collection[]> dataPair) {
			final Collection[] dataSet = dataPair.second;

			if(dataSet.length == 0) {
				Toast.makeText(MainActivity.this,
				               "Network issue; no blogs/collections returned.",
				               Toast.LENGTH_SHORT).show();
			}

			final String[]  inits = MainActivity.super
			                                    .getResources()
			                                    .getStringArray(R.array.blogs_array);
			final String[]  blogs = Arrays.stream(dataSet)
			                              .map((final Collection col) -> col.getTitle())
			                              .toArray((final int size) -> new String[size]);
			final String[] finals = Arrays.copyOf(inits,
			                                      inits.length + blogs.length);

			System.arraycopy(blogs, 0, finals, inits.length, blogs.length);

			final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this.getApplicationContext(),
			                                                              android.R.layout.simple_spinner_item,
			                                                              finals);

			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			dataPair.first.setAdapter(adapter);
		}
	}
}
