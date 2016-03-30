package com.slack.csci3308project.dailyfortune;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends WearableActivity  {

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private BoxInsetLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;

    private GeneralQuoteDataSource generalDatasource;
    private SportsQuoteDataSource sportsDatasource;
    private EducationalQuoteDataSource educationalDatasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        mTextView = (TextView) findViewById(R.id.text);
        //mClockView = (TextView) findViewById(R.id.clock);

        generalDatasource = new GeneralQuoteDataSource(this);
        generalDatasource.open();
        List<GeneralQuote> generalValues = generalDatasource.getAllGeneralQuotes();

        sportsDatasource = new SportsQuoteDataSource(this);
        sportsDatasource.open();
        List<SportsQuote> sportsValue = sportsDatasource.getAllSportsQuotes();

        educationalDatasource = new EducationalQuoteDataSource(this);
        educationalDatasource.open();
        List<EducationalQuote> educationalValues = educationalDatasource.getAllEducationalQuotes();

        EducationalQuote eQuote = educationalDatasource.getRandomEducationalQuote();
    }

    public void onClick(View view){

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



