package ru.dellirium.poecurrencysimulator;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

public class PoeItem {

    Activity activity;

    private int itemSockets;

    public PoeItem (Activity activity) {
        this.activity = activity;
        rollSockets(6);
    }


    public int getSockets () {
        return itemSockets;
    }

 public void rollSockets(int maxNumberOfSockets) {
     Random rand = new Random();
     int n = rand.nextInt(maxNumberOfSockets + 1);
     itemSockets = n;



     ArrayList<ItemSocket> sockets =  new ArrayList<>();

     sockets.add(new ItemSocket(R.id.vaalReg));
     sockets.add(new ItemSocket(R.id.vaalReg));
     sockets.add(new ItemSocket(R.id.vaalReg));
     sockets.add(new ItemSocket(R.id.vaalReg));
     sockets.add(new ItemSocket(R.id.vaalReg));
     sockets.add(new ItemSocket(R.id.vaalReg));

     for (int i = 0; sockets.size() > i; i++) {
         ItemSocket aSocket = sockets.get(i);

         aSocket.getMSocketId();
     }


 }


    public class ItemSocket {
        ImageView mSocket;
        int mSocketId;

        public ItemSocket(int socketID) {

            mSocket = new ImageView(activity);

            Random randomizeColor = new Random();
            int sColor = randomizeColor.nextInt(3);

            switch (sColor) {
                case 0:
                    mSocket.setImageResource(R.drawable.socket_blue);
                    break;
                case 1:
                    mSocket.setImageResource(R.drawable.socket_red);
                    break;
                case 2:
                    mSocket.setImageResource(R.drawable.socket_green);
                    break;
            }

            mSocketId = socketID;

            socketCreate();

        }

        public void socketCreate() {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.RIGHT_OF, mSocketId);

            mSocket.setLayoutParams(params);
            mSocket.generateViewId();

            RelativeLayout item = activity.findViewById(R.id.vaalReg);
            item.addView(mSocket);
        }

        public int getMSocketId() {
            return mSocketId;
        }




    }

}
