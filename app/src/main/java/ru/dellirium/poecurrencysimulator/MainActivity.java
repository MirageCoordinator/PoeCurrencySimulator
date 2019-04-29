package ru.dellirium.poecurrencysimulator;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    String firstTextNumber;

    PoeItem firstText;

    TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstText = new PoeItem(this);

        testView = findViewById(R.id.testView);

        firstTextNumber = String.valueOf(firstText.getSockets());

        testView.setText(firstTextNumber);

        ImageView fusingPicture = findViewById(R.id.fusing);

        View.OnClickListener fusingClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView FIRST_LINK = findViewById(R.id.one_link);

                if (FIRST_LINK.getVisibility() == View.INVISIBLE) {
                    FIRST_LINK.setVisibility(View.VISIBLE);
                } else {
                    FIRST_LINK.setVisibility(View.INVISIBLE);
                }

                firstText.rollSockets(6);

                firstTextNumber = String.valueOf(firstText.getSockets());

                testView.setText(firstTextNumber);


            }
        };

        fusingPicture.setOnClickListener(fusingClick);
    }
}
