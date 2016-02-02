package com.example.zachary.minizhihu;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by zachary on 16/1/11.
 */
public class TitleFragment extends BaseFragment {
    @Bind(R.id.title_list)
    PullToRefreshListView title;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ViewPager mPager;
    private TitleAdapter adapter;
    private List<TitleBean.StoriesEntity> storyList = new ArrayList();
    private ArrayList<BaseFragment> fragmentList;
    public boolean fragmentType = true; // true is homepage ,false is themedaily
    public  static boolean dayOrNight = true;  //better to store in SharedPreferences instead of static
    private View header;
    private ImageView themeImage;
    private DrawerLayout drawerLayout;
    private int selectedId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            fragmentType = getArguments().getBoolean("fragmentType",true);
        }
        InitToolbar();
        InitHeader();
        InitTitle();
        return view;
    }

    //to get instance of drawerlayout from mainactivity to control it to open or close
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        drawerLayout = (DrawerLayout)getActivity().findViewById(R.id.drawerLayout);
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


    //handle info of List of both
    @Override
    public void onInfoChanged(List storiesList, int state) {
        if(getView()==null){
            return;
        }
        List<TitleBean.StoriesEntity> stories = (List<TitleBean.StoriesEntity>) storiesList;
        //initialize adapter and responding Pulldown and Pullup after adapter initialized
        if (state == GetInfoUtil.PULLDOWN) {
            storyList.clear();
        }
        //to make adapter.notifyDataSetChanged(); effective，storyList should point at the same heap
        for (TitleBean.StoriesEntity story : stories) {
            storyList.add(story);
        }
        adapter.notifyDataSetChanged();
        title.onRefreshComplete();
    }

    // handle info of themeDaily image
    @Override
    public void onInfoChanged(Object entity) {
        Picasso.with(getActivity()).load((String)entity).into(themeImage);
    }

    private void InitTitle() {
        //click title item
        title.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position<2){
                    return;
                }
                TitleBean.StoriesEntity story = storyList.get(position - 2);            //position must subtract 2 to make the sequence correct because of the "pulltorefresh" and the "headerView"
                selectedId = story.id;
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ContentFragment contentFragment = new ContentFragment();
                GetInfoUtil.GetExtraInfo(selectedId,contentFragment);
                Bundle args = new Bundle();
                args.putInt("selectedId", selectedId);
                contentFragment.setArguments(args);
                transaction.replace(R.id.activityFrame, contentFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //set Titleadapter and headView
        adapter = new TitleAdapter(getActivity(), R.layout.title_item, storyList);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);     //to add headerView successful this step is necessary but why?
        header.setLayoutParams(layoutParams);
        title.getRefreshableView().addHeaderView(header);
        title.setAdapter(adapter);
    }

    private void InitViewPager(View view) {
        mPager = (ViewPager) view.findViewById(R.id.viewpager);
        fragmentList = new ArrayList<BaseFragment>();
        BaseFragment viewPager1 = new ViewFragment();
        BaseFragment viewPager2 = new ViewFragment();
        BaseFragment viewPager3 = new ViewFragment();
        BaseFragment viewPager4 = new ViewFragment();
        BaseFragment viewPager5 = new ViewFragment();
        fragmentList.add(viewPager1);
        fragmentList.add(viewPager2);
        fragmentList.add(viewPager3);
        fragmentList.add(viewPager4);
        fragmentList.add(viewPager5);
        mPager.setAdapter(new FragmentViewPagerAdapter(getChildFragmentManager(), fragmentList));
        mPager.setCurrentItem(0);//设置当前显示标签页为第一页
    }

    private void InitToolbar() {
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_message:
                    case R.id.action_settings:
                        Toast.makeText(getContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                        break;
                    //change mode of day and night
                    case R.id.action_mode:
                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        TitleFragment titleFragment = new TitleFragment();
                        Bundle args = new Bundle();
                        if (!fragmentType) {        //themeDaily
                            args.putInt("selectedId", selectedId);
                            args.putBoolean("fragmentType", false);
                        }
                        if (dayOrNight) {
                            getActivity().setTheme(R.style.MyThemeNight);
                            dayOrNight = false;
                        } else {
                            getActivity().setTheme(R.style.MyThemeDefault);
                            dayOrNight = true;
                        }
                        titleFragment.setArguments(args);
//                        manager.popBackStack();
                        transaction.replace(R.id.activityFrame, titleFragment, "themefragment");
//                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                }
                return true;
            }
        });
    }

    //initial header of homepage or themedaily
    private void InitHeader() {
        if(fragmentType){
            toolbar.setTitle("首页");
            header= LayoutInflater.from(getActivity()).inflate(R.layout.title_header,null);
            InitViewPager(header);
            GetInfoUtil.GetTitle(GetInfoUtil.PULLDOWN, this, fragmentList);
            title.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    GetInfoUtil.GetTitle(GetInfoUtil.PULLDOWN, TitleFragment.this,fragmentList);
                }
                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    GetInfoUtil.GetTitle(GetInfoUtil.PULLUP, TitleFragment.this,fragmentList);
                }
            });
        }else{
            toolbar.setTitle("主题日报");
            header= LayoutInflater.from(getActivity()).inflate(R.layout.title_themeheader, null);
            selectedId = getArguments().getInt("selectedId");
            themeImage = (ImageView)header.findViewById(R.id.themeImage);
            GetInfoUtil.GetThemeTitle(selectedId, this);
        }
    }
}
