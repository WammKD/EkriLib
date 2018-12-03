package fediverse.writefreely.ekrilib.util;

public class MarkdownUtil {
	private final static String ITALICIZE         = "(?m)(^|[^*\\\\])\\*([^*]+?)\\*([^*\\\\]|$)";
	private final static String ITALICIZE_REPLACE = "$1\\\\\\*\\*$2\\*\\\\\\*$3";
	private final static String      BOLD         = "(?m)(^|[^*\\\\])\\*\\*([^*]*?[^*]+?)\\*\\*([^*\\\\]|$)";
	private final static String      BOLD_REPLACE = "$1\\\\\\*\\\\\\*\\*\\*$2\\*\\*\\\\\\*\\\\\\*$3";
	private final static String      BOTH         = "(?m)(^|[^*\\\\])\\*\\*\\*([^*]*?[^*]*?[^*]+?)\\*\\*\\*([^*\\\\]|$)";
	private final static String      BOTH_REPLACE = "$1\\\\\\*\\\\\\*\\\\\\*\\*\\*\\*$2\\*\\*\\*\\\\\\*\\\\\\*\\\\\\*$3";
	private final static String    STRIKE         = "(?m)(^|[^~\\\\])~~(.+?)~~([^~\\\\]|$)";
	private final static String    STRIKE_REPLACE = "$1\\\\~\\\\~~~$2~~\\\\~\\\\~$3";
	private final static String  HEADER_1         = "(?m)^# (.*)$";
	private final static String  HEADER_1_REPLACE = "# \\\\\\# $1";
	private final static String  HEADER_2         = "(?m)^## (.*)$";
	private final static String  HEADER_2_REPLACE = "## \\\\\\#\\\\\\# $1";
	private final static String  HEADER_3         = "(?m)^### (.*)$";
	private final static String  HEADER_3_REPLACE = "### \\\\\\#\\\\\\#\\\\\\# $1";
	private final static String  HEADER_4         = "(?m)^#### (.*)$";
	private final static String  HEADER_4_REPLACE = "#### \\\\\\#\\\\\\#\\\\\\#\\\\\\# $1";
	private final static String  HEADER_5         = "(?m)^##### (.*)$";
	private final static String  HEADER_5_REPLACE = "##### \\\\\\#\\\\\\#\\\\\\#\\\\\\#\\\\\\# $1";
	private final static String  HEADER_6         = "(?m)^###### (.*)$";
	private final static String  HEADER_6_REPLACE = "###### \\\\\\#\\\\\\#\\\\\\#\\\\\\#\\\\\\#\\\\\\# $1";

	public static String conserveMarkdown(final String content) {
		return content;
	}
}
