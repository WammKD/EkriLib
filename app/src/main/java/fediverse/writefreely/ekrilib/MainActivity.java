package fediverse.writefreely.ekrilib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import com.yydcdut.markdown.MarkdownConfiguration.Builder;
import com.yydcdut.markdown.MarkdownEditText;
import com.yydcdut.markdown.MarkdownProcessor;
import com.yydcdut.markdown.syntax.edit.EditFactory;

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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.getMenuInflater().inflate(R.menu.menu_main, menu);

		return true;
	}
}
