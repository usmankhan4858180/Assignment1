package com.example.assignment1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DEll1 on 1/11/2018.
 */

public class CustomContactAdapter extends ArrayAdapter<User> {

    class ViewHolder {

        TextView myName;
        TextView myPhone;
        Button btn;

        ViewHolder(View v) {
            myName = (TextView) v.findViewById(R.id.name);
            myPhone = (TextView) v.findViewById(R.id.phone);
            btn = (Button) v.findViewById(R.id.call);
        }
    }


    Activity context;
    ArrayList<User> info;

    public CustomContactAdapter(MainActivity context, ArrayList<User> info) {
        super(context, R.layout.contact, info);
        this.context = context;
        this.info = info;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder vh = null;
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.contact, parent, false);
            vh = new ViewHolder(v);
            v.setTag(vh);

        } else {
            vh = (ViewHolder) v.getTag();
            User user = getItem(position);
            vh.myName.setText(user.getName());
            vh.myPhone.setText(user.getPhone());
        }

        vh.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:123456789"));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(callIntent);
            }
        });
        return v;
    }
}
