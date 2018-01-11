package pzm.petchatbot.Utils;

/**
 * Created by pat on 9/29/2016.
 */
public class StaticVars {
    public static final String TONE_ANALYSIS_ENDPOINT = "https://watson-api-explorer.mybluemix.net/tone-analyzer/api/v3/tone?version=2016-05-19&text=";

    public static final String TONE_FILE = "tone.txt";
    public static final String CHAT_CONVERSATION_FILE = "chat.txt";

    public static final int MAX_CHAT_SAVED_LINES = 125;

    public final static String NEW_LINE = System.getProperty("line.separator");

    public static final String CORTICAL_API_KEY = "b93f2100-34fa-11e7-b22d-93a4ae922ff1";
}
