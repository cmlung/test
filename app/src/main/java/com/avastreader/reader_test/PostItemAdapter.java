package com.avastreader.reader_test;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PostItemAdapter extends ArrayAdapter<PostData> {
    private Activity myContext;
    private PostData[] datas;

    public PostItemAdapter(Context context, int textViewResourceId,
                           PostData[] objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        myContext = (Activity) context;
        datas = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = myContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.postitem, null);
        ImageView thumbImageView = (ImageView) rowView
                .findViewById(R.id.postThumb);
        if (datas[position].postThumbUrl == null) {
            thumbImageView.setImageResource(R.drawable.image);
        }

        TextView postTitleView = (TextView) rowView
                .findViewById(R.id.postTitleLabel);
        postTitleView.setText(datas[position].postTitle);

        TextView postDateView = (TextView) rowView
                .findViewById(R.id.postDateLabel);
        postDateView.setText(datas[position].postDate);

        TextView postThumbUrlView = (TextView) rowView
                .findViewById(R.id.postThumbUrlLabel);
        postThumbUrlView.setText(datas[position].postThumbUrl);


        return rowView;
    }
}