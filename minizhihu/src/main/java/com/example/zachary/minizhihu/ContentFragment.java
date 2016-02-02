package com.example.zachary.minizhihu;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.leakcanary.RefWatcher;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zachary on 16/1/12.
 */
public class ContentFragment extends BaseFragment {
    private ExtraInfoBean extraInfo;
    int selectedId;
    @Bind(R.id.contentWebView)
    WebView webView;
    @Bind(R.id.toolbarContent)
    Toolbar toolbar;
    @Bind(R.id.text_thumb)
    TextView textThumb;
    @Bind(R.id.text_comment)
    TextView textComment;
    @Bind(R.id.icon_thumb)
    ImageView iconThumb;
    @Bind(R.id.icon_comment)
    ImageView iconComment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fagment_content, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            selectedId = getArguments().getInt("selectedId");
        }
        InitToolbar();
        InitWebView();
        return view;
    }

    private void InitWebView() {
        webView.loadUrl("http://daily.zhihu.com/story/" + selectedId);
        //to open url in the webview
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);                   // this sentence is important otherwise it can't begin downloading
        
        // to open a downloading interface in default browser
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private void InitToolbar() {
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
            }
        });
        if(extraInfo != null){
            textComment.setText(extraInfo.comments+"");
            textThumb.setText(extraInfo.popularity+"");
        }
        SharedPreferences preferences=getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        Boolean isPopularityAdded = preferences.getBoolean("popularity"+selectedId, false);
        if(isPopularityAdded){
            iconThumb.setImageResource(R.drawable.icon_thumb_green);
        }else{
            iconThumb.setImageResource(R.drawable.icon_thumb);
        }
    }

    @OnClick({R.id.icon_comment,R.id.text_comment} )
    public void getLongComment(){
        CommentFragment commentFragment = new CommentFragment();
        GetInfoUtil.GetLongComment(selectedId, commentFragment);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.activityFrame,commentFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @OnClick({R.id.icon_thumb,R.id.text_thumb})
    public void addPopularity(){
        SharedPreferences preferences=getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Boolean isPopularityAdded = preferences.getBoolean("popularity" + selectedId, false);
        if(isPopularityAdded){
            editor.putBoolean("popularity"+selectedId,false);
            textThumb.setText(--extraInfo.popularity+"");
            iconThumb.setImageResource(R.drawable.icon_thumb);
        }else {
            Toast.makeText(getContext(), "赞"+extraInfo.popularity+"+1", Toast.LENGTH_SHORT).show();
            textThumb.setText(++extraInfo.popularity + "");
            iconThumb.setImageResource(R.drawable.icon_thumb_green);
            editor.putBoolean("popularity"+selectedId,true);
        }
        editor.commit();

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
    public void onInfoChanged(Object entity) {
        extraInfo = (ExtraInfoBean)entity;
        if(textComment!=null && textThumb!=null){
            textComment.setText(extraInfo.comments+"");
            textThumb.setText(extraInfo.popularity+"");
        }
    }

}
