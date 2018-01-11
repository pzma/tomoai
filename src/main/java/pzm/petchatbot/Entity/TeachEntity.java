package pzm.petchatbot.Entity;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by pat on 5/13/2017.
 */
public class TeachEntity extends SugarRecord {
    @Unique
    private
    String userentry;

    private String botreply;

    public TeachEntity(){}

    public TeachEntity(String userEntry, String botReply) {
        this.setUserentry(userEntry);
        this.setBotreply(botReply);
    }

    public String getUserentry() {
        return userentry;
    }

    public void setUserentry(String userentry) {
        this.userentry = userentry;
    }

    public String getBotreply() {
        return botreply;
    }

    public void setBotreply(String botreply) {
        this.botreply = botreply;
    }
}
