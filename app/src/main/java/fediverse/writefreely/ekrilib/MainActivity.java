package fediverse.writefreely.ekrilib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;
import com.yydcdut.markdown.MarkdownConfiguration.Builder;
import com.yydcdut.markdown.MarkdownEditText;
import com.yydcdut.markdown.MarkdownProcessor;
import com.yydcdut.markdown.syntax.edit.EditFactory;
import fediverse.writefreely.ekrilib.util.MarkdownUtil;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MarkdownProcessor markdownProcessor = new MarkdownProcessor(this);
		markdownProcessor.config(new Builder(this).build());
		markdownProcessor.factory(EditFactory.create());
		markdownProcessor.live((MarkdownEditText) findViewById(R.id.plain_text_input));
	}
}
