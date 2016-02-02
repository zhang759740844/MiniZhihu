package com.example.zachary.minizhihu;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zachary on 16/1/11.
 */
public class TitleAdapter extends ArrayAdapter<TitleBean.StoriesEntity>{
    private int resourceId;

    public TitleAdapter(Context context, int resource, List<TitleBean.StoriesEntity> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TitleBean.StoriesEntity title = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.titleText.setText(title.title);
//        viewHolder.titleImage.setImageURI(Uri.parse(title.images));
        if(title.images != null){
            Picasso.with(getContext()).load(title.images.get(0)).into(viewHolder.titleImage);
        }else{
            viewHolder.titleImage.setVisibility(View.GONE);
        }

        return view;
    }

    class ViewHolder {
        @Bind(R.id.title_text)
        TextView titleText;
        @Bind(R.id.title_image)
        ImageView titleImage;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
