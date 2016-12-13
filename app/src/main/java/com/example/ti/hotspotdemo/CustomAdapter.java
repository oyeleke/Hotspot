package com.example.ti.hotspotdemo;

/**
 * Created by ti on 19/05/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ti on 20/04/2016.
 */
public class CustomAdapter extends ArrayAdapter {

    Context _context;
    int layout;
    List<NewItem> detailsList ;

    public CustomAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);

        _context = context;
        layout = resource;
        detailsList = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.newlist_item,parent,false);
        }

        TextView nameTv = (TextView)convertView.findViewById(R.id.name);
        TextView describe =(TextView)convertView.findViewById(R.id.describe);





        NewItem newItem = new NewItem();
        nameTv.setText(newItem.getName());
        describe.setText(newItem.getDescription());







        return convertView;
    }



}

