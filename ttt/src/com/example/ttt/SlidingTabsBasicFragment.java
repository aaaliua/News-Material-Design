/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ttt;


import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aaaliua.actionbarpulltorefresh.PullToRefreshLayout;
import com.aaaliua.actionbarpulltorefresh.PullToRefreshLayout.OnRefreshListener;
import com.aaaliua.adapter.RecyclerViewAdapter;
import com.aaaliua.utils.FragmentViewPagerAdapter;
import com.aaaliua.view.PagerSlidingTabStrip;
import com.aaaliua.widget.PullRefreshLayout;
import com.melnykov.fab.FloatingActionButton;
import com.saulmm.material.fragments.ContentFragment;
import com.saulmm.material.slidingtabs.views.SlidingTabLayout;


@SuppressLint("NewApi")
public class SlidingTabsBasicFragment extends android.support.v4.app.Fragment {

    static final String LOG_TAG = "SlidingTabsBasicFragment";

    /**
     * A custom {@link ViewPager} title strip which looks much like Tabs present in Android v4.0 and
     * above, but is designed to give continuous feedback to the user when scrolling.
     */
    private PagerSlidingTabStrip mSlidingTabLayout;

    /**
     * A {@link ViewPager} which will be used in conjunction with the {@link SlidingTabLayout} above.
     */
    private ViewPager mViewPager;
    private View mView;

    /**
     * Inflates the {@link View} which will be displayed by this {@link Fragment}, from the app's
     * resources.
     */
    
    final String [] TITLES = {"类别", "首页", "热门付费", "热门免费", "创收最高", "畅销付费新品", "热门免费新品", "上升最快"};
    List<Fragment> fragments;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sliding, container, false);
    }

    // BEGIN_INCLUDE (fragment_onviewcreated)
    /**
     * This is called after the {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has finished.
     * Here we can pick out the {@link View}s we need to configure from the content view.
     *
     * We set the {@link ViewPager}'s adapter to be an instance of {@link SamplePagerAdapter}. The
     * {@link SlidingTabLayout} is then given the {@link ViewPager} so that it can populate itself.
     *
     * @param view View created in {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
    	
    	fragments = new ArrayList<Fragment>();
		for(int i = 0 ;i<TITLES.length;i++){
			if(i == 0){
				Fragment fm =new RecyclerViewFragment();
				fragments.add(fm);
			}else if(i == 1){
				Fragment fm = new ActionbarRecyclerViewFragment();
				fragments.add(fm);
			}else{
				
				Fragment fm = new ContentFragment();
				fragments.add(fm);
			}
		}
    	
        mView = (View) view.findViewById(R.id.layer);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyframPagerAdapter(getActivity().getSupportFragmentManager(),mViewPager,fragments));
        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (PagerSlidingTabStrip) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        
        
        mSlidingTabLayout.setIndicatorColor(getResources().getColor(android.R.color.white));
        mSlidingTabLayout.setIndicatorHeight(12);
        mSlidingTabLayout.setTextColorResource(android.R.color.white);
//        mSlidingTabLayout.setDividerColor(getResources().getColor(android.R.color.transparent));//间距线条
        // END_INCLUDE (setup_slidingtablayout)
    }
    // END_INCLUDE (fragment_onviewcreated)

    /**
     * The {@link android.support.v4.view.PagerAdapter} used to display pages in this sample.
     * The individual pages are simple and just display two lines of text. The important section of
     * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
     * {@link SlidingTabLayout}.
     */
//    class SamplePagerAdapter extends PagerAdapter {
//        final String [] TITLES = {"类别", "首页", "热门付费", "热门免费", "创收最高", "畅销付费新品", "热门免费新品", "上升最快"};
//
//        /**
//         * @return the number of pages to display
//         */
//        @Override
//        public int getCount() {
//            return TITLES.length;
//        }
//
//        /**
//         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
//         * same object as the {@link View} added to the {@link ViewPager}.
//         */
//        @Override
//        public boolean isViewFromObject(View view, Object o) {
//            return o == view;
//        }
//
//        // BEGIN_INCLUDE (pageradapter_getpagetitle)
//        /**
//         * Return the title of the item at {@code position}. This is important as what this method
//         * returns is what is displayed in the {@link SlidingTabLayout}.
//         * <p>
//         * Here we construct one using the position value, but for real application the title should
//         * refer to the item's contents.
//         */
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return TITLES[position];
//        }
//        // END_INCLUDE (pageradapter_getpagetitle)
//
//        /**
//         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
//         * inflate a layout from the apps resources and then change the text view to signify the position.
//         */
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            // Inflate a new layout from our resources
//            View view = getActivity().getLayoutInflater().inflate(R.layout.item_sliding_pager,
//                    container, false);
//            // Add the newly created View to the ViewPager
//            final ImageView img = (ImageView)view.findViewById(R.id.b1);
//            
//            img.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//
//				    Intent intent = new Intent(getActivity(),Test.class);
//		            String transitionName = "aa";
//		            
//		            ActivityOptionsCompat options =
//		            		ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//		            			img,   // The view which starts the transition
//		            		    transitionName    // The transitionName of the view we’re transitioning to
//		            		    );
//					
//					
//					ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
//				}
//			});
//        
//            
//            container.addView(view);
//
//
//            // Return the View
//            return view;
//        }
//
//        /**
//         * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
//         * {@link View}.
//         */
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//            Log.i(LOG_TAG, "destroyItem() [position: " + position + "]");
//        }
//
//    }
    
    
    
    
    
    
    public class MyframPagerAdapter extends FragmentViewPagerAdapter {

    	
		
		public MyframPagerAdapter(FragmentManager fragmentManager,
				ViewPager viewPager, List<Fragment> fragments) {
			super(fragmentManager, viewPager, fragments);
		}

		//为指示器显示标题使用
		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}
		

	}
    
    
    
    
   
    
    public static class RecyclerViewFragment extends Fragment {
    	PullRefreshLayout layout;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_recyclerview, container, false);

            layout = (PullRefreshLayout) root.findViewById(R.id.swipeRefreshLayout);
            final RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

            RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), getResources()
                .getStringArray(R.array.countries),getActivity());
            recyclerView.setAdapter(adapter);

            FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);
            fab.attachToRecyclerView(recyclerView);

            
            layout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
            layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    layout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            layout.setRefreshing(false);
                        }
                    }, 4000);
                }
            });
            return root;
        }
    }
    
    
    
    
    public static class ActionbarRecyclerViewFragment extends Fragment implements OnRefreshListener{
    	PullToRefreshLayout layout;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_actionrecyclerview, container, false);

            	
            layout = (PullToRefreshLayout) root.findViewById(R.id.pull_to_refresh_layout);
            layout.setOnRefreshListener(this);
            final RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

            RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), getResources()
                .getStringArray(R.array.countries),getActivity());
            recyclerView.setAdapter(adapter);

            FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);
            fab.attachToRecyclerView(recyclerView);

            
            return root;
        }
		@Override
		public void onRefresh() {
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
				}
			}, 3000);
		}
    }
    
    
//    switch (id){
//    case R.id.action_circles:
//        layout.setRefreshStyle(PullRefreshLayout.STYLE_CIRCLES);
//        return true;
//    case R.id.action_water_drop:
//        layout.setRefreshStyle(PullRefreshLayout.STYLE_WATER_DROP);
//        return true;
//    case R.id.action_ring:
//        layout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
//        return true;
//}
    
}
