package com.example.zachary.pulltorefresh;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.LinkedList;

public class MainActivity extends Activity
{

    private LinkedList<String> mListItems;
    /**
     * 上拉刷新的控件
     */
    private PullToRefreshListView mPullRefreshListView;

    private ArrayAdapter<String> mAdapter;

    private int mItemCount = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 得到控件
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
        //初始化数据
        initDatas();
        //设置适配器
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mListItems);
        mPullRefreshListView.setAdapter(mAdapter);
        // 设置监听事件
        mPullRefreshListView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>()
                {
                    @Override
                    public void onRefresh(
                            PullToRefreshBase<ListView> refreshView)
                    {
                        String label = DateUtils.formatDateTime(
                                getApplicationContext(),
                                System.currentTimeMillis(),
                                DateUtils.FORMAT_SHOW_TIME
                                        | DateUtils.FORMAT_SHOW_DATE
                                        | DateUtils.FORMAT_ABBREV_ALL);
                        // 显示最后更新的时间
                        refreshView.getLoadingLayoutProxy()
                                .setLastUpdatedLabel(label);

                        // 模拟加载任务
                        new GetDataTask().execute();
                    }
                });

    }

    private void initDatas()
    {
        // 初始化数据和数据源
        mListItems = new LinkedList<String>();

        for (int i = 0; i < mItemCount; i++)
        {
            mListItems.add("" + i);
        }
    }

    private class GetDataTask extends AsyncTask<Void, Void, String>
    {

        @Override
        protected String doInBackground(Void... params)
        {
            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
            }
            return "" + (mItemCount++);
        }

        @Override
        protected void onPostExecute(String result)
        {
            mListItems.add(result);
            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }
    }

}