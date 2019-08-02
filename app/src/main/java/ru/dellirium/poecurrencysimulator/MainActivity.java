package ru.dellirium.poecurrencysimulator;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private PoeItem vaalRegalia;
    private View[] linksList;
    private ConstraintLayout mainLayout;
    private TextView usedSockets;
    private TextView usedFusings;
    private int counterSockets = 0;
    private int counterFusings = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainConstraint);
        usedSockets = findViewById(R.id.used_sockets);
        usedFusings = findViewById(R.id.used_fusings);

        vaalRegalia = new PoeItem(6,0,0,190);
        initialItemDraw(vaalRegalia);

        //region Clicks handler
        ImageView jewellerImage = findViewById(R.id.jeweller);
        ImageView fusingImage = findViewById(R.id.fusing);
        ImageView chromaticImage = findViewById(R.id.chromatic);

        View.OnClickListener currencyClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.jeweller:
                        vaalRegalia.rollSockets();
                        drawSockets(vaalRegalia);
                        if (vaalRegalia.getItemSockets().length != vaalRegalia.getMaxNumberOfSockets()) {
                            counterSockets++;
                            usedSockets.setText(getApplicationContext().getString(R.string.used_sockets, counterSockets));
                        }
                        break;
                    case R.id.fusing:
                        vaalRegalia.rollLinks();
                        drawLinks(vaalRegalia);
                        if (!vaalRegalia.isAlreadySixLinked) {
                            counterFusings++;
                            usedFusings.setText(getApplicationContext().getString(R.string.used_fusings, counterFusings));
                        }
                        break;
                    case R.id.chromatic:
                        vaalRegalia.rollColors();
                        drawColors(vaalRegalia);
                        break;
                }
            }
        };
        jewellerImage.setOnClickListener(currencyClick);
        fusingImage.setOnClickListener(currencyClick);
        chromaticImage.setOnClickListener(currencyClick);
        //endregion
    }

    //region Item drawing methods
    private void initialItemDraw(PoeItem item) {
        itemInflate();
        fillLinksList();
        drawSockets(item);
    }

    private void itemInflate() {
        getLayoutInflater().inflate(R.layout.single_poe_item, mainLayout, true);
    }

    private void fillLinksList() {
        final short childCount = (short) mainLayout.getChildCount();

        linksList = new View[5];
        byte linksListCounter = 0;
        for (int i = 0; i < childCount; i++) {
            View child = mainLayout.getChildAt(i);
            if (child instanceof ImageView) {
                final Object tag = child.getTag();
                if (tag != null && tag.equals("link")) {
                    linksList[linksListCounter] = child;
                    linksListCounter++;
                    child.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    private void drawSockets(PoeItem item) {
            GridLayout socketsList = findViewById(R.id.sockets_list);
            View[] socketsListInRightOrder = new View[item.getMaxNumberOfSockets()];

            // Swap 2 and 3 socket position
            for (int i = 0; i < item.getMaxNumberOfSockets(); i++) {
                if (i == 2) {
                    socketsListInRightOrder[3] = socketsList.getChildAt(i);
                } else if (i == 3) {
                    socketsListInRightOrder[2] = socketsList.getChildAt(i);
                } else {
                    socketsListInRightOrder[i] = socketsList.getChildAt(i);
                }
            }

            for (int i = 0; i < socketsListInRightOrder.length; i++) {
                if (i < item.getItemSockets().length) {
                    socketsListInRightOrder[i].setVisibility(View.VISIBLE);
                } else {
                    socketsListInRightOrder[i].setVisibility(View.GONE);
                }
            }
            drawLinks(item);
            drawColors(item);
        if (item.getMaxNumberOfSockets() == item.getItemSockets().length) {
            Toast.makeText(this, "Item has maximum number of sockets", Toast.LENGTH_SHORT).show();
        }
    }


    private void drawColors(PoeItem item) {
        GridLayout socketsList = findViewById(R.id.sockets_list);
        for (int i = 0; i < item.getItemSockets().length; i++) {
            ImageView currentSocket = (ImageView) socketsList.getChildAt(i);
            currentSocket.setImageResource(item.getItemSockets()[i].getColor());
        }
    }

    private void drawLinks(PoeItem item) {
            for (int i = 0; i < item.getItemLinks().length; i++) {
                if (item.getItemLinks()[i]) {
                    linksList[i].setVisibility(View.VISIBLE);
                } else {
                    linksList[i].setVisibility(View.GONE);
                }
            }
        if (item.isAlreadySixLinked) {
            Toast.makeText(this, "Item already has six linked sockets", Toast.LENGTH_SHORT).show();
        }
    }
    //endregion
}
