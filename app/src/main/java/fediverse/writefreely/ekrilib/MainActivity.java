package fediverse.writefreely.ekrilib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.getMenuInflater().inflate(R.menu.menu_main, menu);

		return true;
	}
}
