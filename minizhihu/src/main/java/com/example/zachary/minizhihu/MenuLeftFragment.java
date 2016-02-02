package com.example.zachary.minizhihu;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zachary on 16/1/18.
 */
public class MenuLeftFragment extends BaseFragment {
    @Bind(R.id.menu_list)
    ListView listView;
    private List<String>nameList = new ArrayList();
    private List<MenuBean.ThemeEntity> themeList = new ArrayList<>();
    private ArrayAdapter menuAdapter;
    private DrawerLayout drawerLayout;
//    private View menuHeader;
//    private static boolean fromHomepageOrThemeDaily=false ;   //0isfrom homepage
    @Override
    public void onInfoChanged(List entity) {
        themeList =(List<MenuBean.ThemeEntity>)entity;
        nameList.clear();
        for(MenuBean.ThemeEntity theme:themeList){
            nameList.add(theme.name);
        }
        menuAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final FragmentManager manager = getFragmentManager();
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        View menuHeader = LayoutInflater.from(getActivity()).inflate(R.layout.menu_header, null);
        TextView homePage = (TextView)menuHeader.findViewById(R.id.homePage);
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = manager.beginTransaction();
                TitleFragment titleFragment = new TitleFragment();
                transaction.replace(R.id.activityFrame, titleFragment);
                transaction.commit();

                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawers();
                }
            }
        });

        ButterKnife.bind(this, view);
        GetInfoUtil.GetMenu(this);
        menuAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,nameList);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.WRAP_CONTENT );     //to add headerView successful this step is necessary but why?
        menuHeader.setLayoutParams(layoutParams);
        listView.addHeaderView(menuHeader);
        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < 1) {
                    return;
                }
                MenuBean.ThemeEntity theme = themeList.get(position - 1);
                int selectedId = theme.id;
//                if(manager.getBackStackEntryCount()==0){
//                    fromHomepageOrThemeDaily = false;
//                }
                FragmentTransaction transaction = manager.beginTransaction();
                TitleFragment titleFragment = new TitleFragment();
                Bundle args = new Bundle();
                args.putInt("selectedId", selectedId);
                args.putBoolean("fragmentType", false);
                titleFragment.setArguments(args);
                transaction.replace(R.id.activityFrame, titleFragment);
//                if(!fromHomepageOrThemeDaily){//it's very very important to use this flag instead of popbackstack each time it was clicked
//                    transaction.addToBackStack(null);
//                    fromHomepageOrThemeDaily = true;
//                }
                transaction.commit();

                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawers();
                }
            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        drawerLayout = (DrawerLayout)getActivity().findViewById(R.id.drawerLayout);
    }
//    @OnClick
//    public void ClickItem()
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


}