package fediverse.writefreely.ekrilib.util;

public class MarkdownUtil {
	private final static String      BOLD         = "(?m)(^|[^*\\\\])\\*\\*(.+?)\\*\\*([^*\\\\]|$)";
	private final static String      BOLD_REPLACE = "$1\\\\\\*\\\\\\*\\*\\*$2\\*\\*\\\\\\*\\\\\\*$3";
	private final static String ITALICIZE         = "(?m)(^|[^*\\\\])\\*([^*]+?)\\*([^*\\\\]|$)";
	private final static String ITALICIZE_REPLACE = "$1\\\\\\*\\*$2\\*\\\\\\*$3";

	public static String conserveMarkdown(final String content) {
		return content.replaceAll(     BOLD,      BOLD_REPLACE)
		              .replaceAll(ITALICIZE, ITALICIZE_REPLACE);
	}
}
