package com.example.vijaya.myorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class SummaryPage extends AppCompatActivity {
    TextView summaryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // display order summary details
        summaryView = findViewById(R.id.tw_summary);
        summaryView.setText(Html.fromHtml("Thank you for your Pizza order" + "<br/>" + "<br/><br/>"));
        summaryView.append(getIntent().getStringExtra("summary"));
        summaryView.setVisibility(View.VISIBLE);

    }
    public void goToOrder(View view) {
        Intent direct = new Intent(SummaryPage.this, MainActivity.class);
        startActivity(direct);
    }

}
