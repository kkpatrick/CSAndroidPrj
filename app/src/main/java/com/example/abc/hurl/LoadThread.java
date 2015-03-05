package com.example.abc.hurl;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import de.greenrobot.event.EventBus;

/**
 * Created by abc on 3/5/15.
 */
public class LoadThread extends Thread{
    static final String SO_URL=
            "https://api.stackexchange.com/2.1/questions?"
            + "order=desc&sort=creation&site=stackoverflow&tagged=android";

    @Override
    public void run() {
        try {
            //Creates an HttpUrlConnection by creating a URL for our Stack Exchange API endpoint and opening a connection
            HttpURLConnection c=
                    (HttpURLConnection)new URL(SO_URL).openConnection();
            try {
                //Creates a BufferedReader wrapped around the InputStream from the HTTP connection
                InputStream in = c.getInputStream();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                //Parses the JSON we get back from that HTTP request via a Gson instance, loading the data into an instance of our SOQuestions
                SOQuestions questions =
                        new Gson().fromJson(reader, SOQuestions.class);
                //Close the BufferedReader (and the InputStream by extension)
                reader.close();
                //Post a QuestionsLoadedEvent to greenrobot’s EventBus, to let somebody know that our questions exist
                EventBus.getDefault().post(new QuestionsLoadedEvent(questions));
            } catch (IOException e) {
                Log.e(getClass().getSimpleName(), "Exception parsing JSON", e);
            }
            finally {
                c.disconnect();
            }
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Exception parsing JSON", e);
        }
    }
}
