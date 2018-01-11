package pzm.petchatbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    public EditText feedback;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedback = (EditText) findViewById(R.id.feedbackRequestText);

        addListenerOnRatingBar();
    }

    public void sendEmail(View v) {

        String body = "";
        body+="Stars: " + ratingBar.getRating() +"\n";
        body += "Feedback:\n";
        body += feedback.getText().toString();


        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"peteyzeepablo@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Request: ComicViewer");
        i.putExtra(Intent.EXTRA_TEXT, body);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(FeedbackActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
            }
        });
    }
}
