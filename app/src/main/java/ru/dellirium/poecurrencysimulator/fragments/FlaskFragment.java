package ru.dellirium.poecurrencysimulator.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import ru.dellirium.poecurrencysimulator.R;


public class FlaskFragment extends Fragment {
    private ImageButton toolbar;

    public static Fragment getInstance() {
        return new FlaskFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flask, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO

        View.OnClickListener currencyClick = v -> {
            switch (v.getId()) {
                case R.id.toolbar:
                    if (getActivity() != null) {
                        DrawerLayout drawerLayout = getActivity().findViewById(R.id.drawer_layout);
                        drawerLayout.openDrawer(Gravity.START);
                    }
                    break;
            }
        };
        toolbar.setOnClickListener(currencyClick);
    }
}
