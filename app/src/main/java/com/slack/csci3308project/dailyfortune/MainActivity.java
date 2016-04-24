package com.slack.csci3308project.dailyfortune;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends WearableActivity  {

    private static final int ALARM_SEARCH_REQUEST_CODE = 237;

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private static final int DELAY_AFTER_ALARM = 5; //in minutes

    private QuotesTopics quotesTopics = new QuotesTopics();

    private static final long ALARM_DELAY_TIME = 60000*10;

    private BoxInsetLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;
    private AlarmReceiver ar;
    private PendingIntent pendingIntent;

    private GeneralQuoteDataSource generalDatasource;
    private SportsQuoteDataSource sportsDatasource;
    private EducationalQuoteDataSource educationalDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        TextView quoteTextView = (TextView) findViewById(R.id.quote);
        //TextView authorTextView = (TextView) findViewById(R.id.author);

        generalDatasource = new GeneralQuoteDataSource(this);
        generalDatasource.open();
        List<GeneralQuote> generalValues = generalDatasource.getAllGeneralQuotes();

        sportsDatasource = new SportsQuoteDataSource(this);
        sportsDatasource.open();
        List<SportsQuote> sportsValue = sportsDatasource.getAllSportsQuotes();

        educationalDatasource = new EducationalQuoteDataSource(this);
        educationalDatasource.open();
        List<EducationalQuote> educationalValues = educationalDatasource.getAllEducationalQuotes();

        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);
        //this.searchAlarms();
    }

    /**
     * Searches for alarms, and sets a timer to display a quote at alarm trigger time.
     */
    public void searchAlarms() {
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        AlarmManager.AlarmClockInfo nextAlarm = alarmManager.getNextAlarmClock();
        if (nextAlarm == null) {
            return;
        }

        Intent intent = new Intent(this, AlarmReceiver.class);
        long triggerTime = nextAlarm.getTriggerTime() + ALARM_DELAY_TIME;
        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                triggerTime, PendingIntent.getBroadcast(this, 0, intent, 0));
    }

    /**
     * Displays an educational quote when someone clicks the "Educational" button
     * @param target the View sent by the View which triggered this event
     */
    public void onClickEducational(View target){
        educationalDatasource = new EducationalQuoteDataSource(this);
        educationalDatasource.open();
        TextView quoteTextView = (TextView) findViewById(R.id.quote);
        EducationalQuote randomEQuote = educationalDatasource.getRandomEducationalQuote();

        String eduQuote = randomEQuote.getQuote();
        String eduAuthor = randomEQuote.getAuthor();
        String eduQuoteAuthor = eduQuote + "\n -" + eduAuthor;
        quoteTextView.setText(eduQuoteAuthor);

        View generalButton = findViewById(R.id.button);
        generalButton.setVisibility(View.GONE);
        View sportsButton = findViewById(R.id.button3);
        sportsButton.setVisibility(View.GONE);
        View educationalButton = findViewById(R.id.button2);
        educationalButton.setVisibility(View.GONE);
        View moreQuotesButton = findViewById(R.id.button4);
        moreQuotesButton.setVisibility(View.VISIBLE);
    }

    /**
     * Displays a sports quote when someone clicks the "Educational" button
     * @param target the View sent by the View which triggered this event
     */
    public void onClickSports(View target){
        sportsDatasource = new SportsQuoteDataSource(this);
        sportsDatasource.open();
        TextView quoteTextView = (TextView) findViewById(R.id.quote);
        SportsQuote randomSQuote = sportsDatasource.getRandomSportsQuote();

        String spQuote = randomSQuote.getQuote();
        String spAuthor = randomSQuote.getAuthor();
        String spQuoteAuthor = spQuote + "\n -" + spAuthor;
        quoteTextView.setText(spQuoteAuthor);

        View generalButton = findViewById(R.id.button);
        generalButton.setVisibility(View.GONE);
        View sportsButton = findViewById(R.id.button3);
        sportsButton.setVisibility(View.GONE);
        View educationalButton = findViewById(R.id.button2);
        educationalButton.setVisibility(View.GONE);
        View moreQuotesButton = findViewById(R.id.button4);
        moreQuotesButton.setVisibility(View.VISIBLE);
    }

    /**
     * Displays a general quote when someone clicks the "Educational" button
     * @param target the View sent by the View which triggered this event
     */
    public void onClickGeneral(View target){
        generalDatasource = new GeneralQuoteDataSource(this);
        generalDatasource.open();
        TextView quoteTextView = (TextView) findViewById(R.id.quote);
        GeneralQuote randomGQuote = generalDatasource.getRandomGeneralQuote();

        String genQuote = randomGQuote.getQuote();
        String genAuthor = randomGQuote.getAuthor();
        String genQuoteAuthor = genQuote + "\n -" + genAuthor;
        quoteTextView.setText(genQuoteAuthor);

        View generalButton = findViewById(R.id.button);
        generalButton.setVisibility(View.GONE);
        View sportsButton = findViewById(R.id.button3);
        sportsButton.setVisibility(View.GONE);
        View educationalButton = findViewById(R.id.button2);
        educationalButton.setVisibility(View.GONE);
        View moreQuotesButton = findViewById(R.id.button4);
        moreQuotesButton.setVisibility(View.VISIBLE);
    }

    /**
     * Allows the user to choose another quote after one is displayed
     * @param target the View sent by the View which triggered this event
     */
    public void onClickMore(View target){
        View generalButton = findViewById(R.id.button);
        generalButton.setVisibility(View.VISIBLE);
        View sportsButton = findViewById(R.id.button3);
        sportsButton.setVisibility(View.VISIBLE);
        View educationalButton = findViewById(R.id.button2);
        educationalButton.setVisibility(View.VISIBLE);
        View moreQuotesButton = findViewById(R.id.button4);
        moreQuotesButton.setVisibility(View.GONE);

        TextView quoteTextView = (TextView) findViewById(R.id.quote);
        quoteTextView.setText("");
    }

    @Override
    protected void onResume(){
        generalDatasource.open();
        super.onResume();
    }

    @Override
    protected void onPause(){
        generalDatasource.close();
        super.onPause();
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    /**
     * Changes the display based on if ambient has been selected or not.
     */
    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mTextView.setTextColor(getResources().getColor(android.R.color.white));
            mClockView.setVisibility(View.VISIBLE);

            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        } else {
            mContainerView.setBackground(null);
            mTextView.setTextColor(getResources().getColor(android.R.color.black));
            mClockView.setVisibility(View.GONE);
        }
    }
}



