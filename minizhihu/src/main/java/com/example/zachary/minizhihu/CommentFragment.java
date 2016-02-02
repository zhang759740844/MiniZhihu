package com.example.zachary.minizhihu;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.leakcanary.RefWatcher;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zachary on 16/1/26.
 */
public class CommentFragment extends BaseFragment {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.commentList)
    ListView listView;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.writeComment)
    ImageView writeComment;
    private List<CommentBean.CommentEntity> commentList = new ArrayList<>();
    private CommentAdapter commentAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment,container,false);
        ButterKnife.bind(this, view);
        commentAdapter = new CommentAdapter(getContext(),R.layout.comment_item,commentList);
        listView.setAdapter(commentAdapter);
        commentAdapter.notifyDataSetChanged();
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
                Toast.makeText(getContext(), "aaa", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @OnClick(R.id.writeComment)
    public void writeComment(){
        Toast.makeText(getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = ExampleApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    @Override
    public void onInfoChanged(List entity) {
        commentList.clear();
        if(title!=null){
            title.setText(entity.size()+"条评论");
        }

        for(CommentBean.CommentEntity comment:(List<CommentBean.CommentEntity>)entity){
            commentList.add(comment);
        }
        if(commentAdapter!=null){
            commentAdapter.notifyDataSetChanged();
        }

    }
}
