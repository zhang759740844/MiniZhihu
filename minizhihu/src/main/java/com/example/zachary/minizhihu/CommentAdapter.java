package com.example.zachary.minizhihu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zachary on 16/1/26.
 */
public class CommentAdapter extends ArrayAdapter<CommentBean.CommentEntity> {
    private int resourceId;
    public CommentAdapter(Context context, int resource, List<CommentBean.CommentEntity> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentBean.CommentEntity comment = getItem(position);
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
        viewHolder.commentName.setText(comment.author);
        viewHolder.commentContent.setText(comment.content);
        Picasso.with(getContext()).load(comment.avatar).into(viewHolder.commentAvatar);
        return view;
    }

    class ViewHolder {
        @Bind(R.id.commentName)
        TextView commentName;
        @Bind(R.id.commentAvatar)
        CircleImageView commentAvatar;
        @Bind(R.id.commentContent)
        TextView commentContent;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
