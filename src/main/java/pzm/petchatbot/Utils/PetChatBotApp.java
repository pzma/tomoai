package pzm.petchatbot.Utils;

import android.app.Application;
import android.content.Context;
import com.orm.SugarContext;

/**
 * Created by pat on 9/29/2016.
 */
public class PetChatBotApp extends Application {
    private static PetChatBotApp instance;

    public static PetChatBotApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        SugarContext.init(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }


}