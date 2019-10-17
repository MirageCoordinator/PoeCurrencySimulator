package ru.dellirium.poecurrencysimulator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import ru.dellirium.poecurrencysimulator.FlasksViewModel;
import ru.dellirium.poecurrencysimulator.R;
import ru.dellirium.poecurrencysimulator.items.affixes.Affix;


public class FlaskFragment extends Fragment {
    private ImageButton toolbar;
    private FlasksViewModel model;
    private TextView itemName;
    private TextView itemExplicit;

    public static Fragment getInstance() {
        return new FlaskFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flask, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        itemName = view.findViewById(R.id.item_name);
        itemExplicit = view.findViewById(R.id.explicits);
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
                        drawerLayout.openDrawer(GravityCompat.START);
                    }
                    break;
            }
        };
        toolbar.setOnClickListener(currencyClick);

        model = ViewModelProviders.of(this).get(FlasksViewModel.class);
        model.createSomeMods();

        Affix flaskCharges = model.getMod("FlaskExtraCharges1");
        Affix curseImmunity = model.getMod("FlaskCurseImmunity1");

        itemExplicit.setText(setBlueItemText(flaskCharges, curseImmunity));
        itemName.setText(setFlaskName(flaskCharges, curseImmunity));
    }

    private String setBlueItemText(Affix prefix, Affix suffix) {
        return prefix.effect + "\n" + suffix.effect;
    }

    private String setFlaskName(Affix prefix, Affix suffix) {
        return prefix.name + " Flask " + suffix.name;
    }
}
