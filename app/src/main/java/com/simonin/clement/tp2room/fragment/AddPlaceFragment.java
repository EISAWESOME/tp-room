package com.simonin.clement.tp2room.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.simonin.clement.tp2room.R;
import com.simonin.clement.tp2room.database.AppDatabase;
import com.simonin.clement.tp2room.database.entity.Place;

public class AddPlaceFragment extends Fragment implements View.OnClickListener {

    private EditText name;
    private EditText description;
    private EditText phone;
    private EditText latitude;
    private EditText longitude;

    private Button insert;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_add_place, container, false);
        View rootView = inflater.inflate(R.layout.fragment_add_place, container, false);

        name = rootView.findViewById(R.id.add_place_name);
        description = rootView.findViewById(R.id.add_place_description);
        phone = rootView.findViewById(R.id.add_place_phone);
        latitude = rootView.findViewById(R.id.add_place_latitude);
        longitude = rootView.findViewById(R.id.add_place_longitude);

        insert = rootView.findViewById(R.id.add_place_insert);

        insert.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Place placeToInsert = new Place();

        final String nameTxt = String.valueOf(name.getText());

        if (TextUtils.isEmpty(nameTxt)) {
            name.setError(getString(R.string.field_error));
            return;
        }


        final String descriptionTxt = String.valueOf(description.getText());

        if (TextUtils.isEmpty(descriptionTxt)) {
            description.setError(getString(R.string.field_error));
            return;
        }


        final String phoneTxt = String.valueOf(phone.getText());

        if (TextUtils.isEmpty(phoneTxt) || phoneTxt.length() != 10) {
            phone.setError(getString(R.string.field_error));
            return;
        }


        final String latitudeTxt = String.valueOf(latitude.getText());

        if (TextUtils.isEmpty(latitudeTxt)) {
            latitude.setError(getString(R.string.field_error));
            return;
        }


        final String longitudeTxt = String.valueOf(longitude.getText());

        if (TextUtils.isEmpty(longitudeTxt)) {
            longitude.setError(getString(R.string.field_error));
            return;
        }

        try {
            placeToInsert.setName(String.valueOf(name.getText()));
            placeToInsert.setDescription(String.valueOf(description.getText()));
            placeToInsert.setPhone(String.valueOf(phone.getText()));
            placeToInsert.setLatitude(Float.parseFloat(String.valueOf(latitude.getText())));
            placeToInsert.setLongitude(Float.parseFloat(String.valueOf(longitude.getText())));

        } catch (Exception e) {

        }

        AppDatabase.get(getContext()).placeDao().insert(placeToInsert);

        CharSequence text = "Place ajoutée !";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(getContext(), text, duration);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 10);
        toast.show();
    }

}
