package com.example.zachary.clock;


import android.animation.TimeAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by zachary on 16/1/11.
 */
public class TitleFragment extends Fragment implements OnInfoChangedListener,View.OnClickListener{
    @Bind(R.id.title_list)
    PullToRefreshListView title;
    TimePicker timePick1;

    private  TitleAdapter adapter;
    private List<TitleBean.StoriesEntity> storyList= new ArrayList<>();
    public ArrayList<Fragment> fragmentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        ButterKnife.bind(this, view);

        GetInfoUtil.GetTitle(GetInfoUtil.PULLDOWN, this);
        title.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                GetInfoUtil.GetTitle(GetInfoUtil.PULLDOWN, TitleFragment.this);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                GetInfoUtil.GetTitle(GetInfoUtil.PULLUP, TitleFragment.this);
            }
        });
        title.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TitleBean.StoriesEntity story = storyList.get(position - 2);            //position must subtract 2 to make the sequence correct because of the "pulltorefresh" and the "headerView"
                int selectedId = story.id;
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ContentFragment contentFragment = new ContentFragment();
                Bundle args = new Bundle();
                args.putInt("selectedId", selectedId);
                contentFragment.setArguments(args);
                transaction.add(R.id.activityFrame, contentFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //set Titleadapter and headView
        adapter = new TitleAdapter(getActivity(),R.layout.title_item,storyList);

        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);     //to add headerView successful this step is necessary but why?
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.title_header, title, false);

        timePick1 = (TimePicker)header.findViewById(R.id.timePicker);
        timePick1.setIs24HourView(true);

        //这边应该写个initheader的方法！！！！！！

        Button button_cancel = (Button)header.findViewById(R.id.button_cancel);
        Button button_single = (Button)header.findViewById(R.id.button_single);
        Button button_repeat = (Button)header.findViewById(R.id.button_repeat);

        button_cancel.setOnClickListener(this);
        button_single.setOnClickListener(this);
        button_repeat.setOnClickListener(this);
//                .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),"ads",Toast.LENGTH_SHORT).show();
//            }
//        });
        header.setLayoutParams(layoutParams);

        title.getRefreshableView().addHeaderView(header);
        title.setAdapter(adapter);



        return view;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_cancel:
                Intent intent = new Intent(getActivity(),MyService.class);
                intent.putExtra("flag", MyService.STOP);
                getActivity().startService(intent);
                Toast.makeText(getActivity(), "取消成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_single:
                int h = timePick1.getCurrentHour();
                int m = timePick1.getCurrentMinute();
                Intent startService = new Intent(getActivity(),MyService.class);
                startService.putExtra("hour",h);
                startService.putExtra("minute", m);
                startService.putExtra("flag", MyService.RUN);
                getActivity().startService(startService);
                Toast.makeText(getActivity(), "单次闹铃设置成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_repeat:
                int h_R = timePick1.getCurrentHour();
                int m_R = timePick1.getCurrentMinute();
                Intent startService_R = new Intent(getActivity(),MyService.class);
                startService_R.putExtra("hour",h_R);
                startService_R.putExtra("minute", m_R);
                startService_R.putExtra("flag", MyService.RUN_REPEAT);
                getActivity().startService(startService_R);
                Toast.makeText(getActivity(), "多次闹铃设置成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //handle info
    @Override
    public void onInfoChanged(List<TitleBean.StoriesEntity> stories,int state) {
        //initialize adapter and responding Pulldown and Pullup after adapter initialized
        if(state == GetInfoUtil.PULLDOWN){
            storyList.clear();
        }
        //to make adapter.notifyDataSetChanged(); effective，storyList should point at the same heap
        for(TitleBean.StoriesEntity story:stories){
            storyList.add(story);
        }
        adapter.notifyDataSetChanged();
        title.onRefreshComplete();
    }

    @Override
    public void onInfoChanged(TitleBean.TopStoryEntity topStoryEntity) {

    }



}

