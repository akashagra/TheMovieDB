package com.example.avma1997.tmdb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MoviesFragmentListItemClick, Fragmenttabtv1.MoviesFragmentListItemClick {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private MoviesPagerAdapter mMoviesPagerAdapter;
    private TVPagerAdapter mTVPagerAdapter;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

       int icon = 0;
    TabLayout tab;
    String fullname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i=getIntent();
        fullname= i.getStringExtra("name");




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mMoviesPagerAdapter = new MoviesPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMoviesPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
        tab = (TabLayout) findViewById(R.id.tab);
        tab.setupWithViewPager(mViewPager);
        tab.setTabGravity(TabLayout.GRAVITY_CENTER);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
//        fragments=new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                int id = item.getItemId();
                if (id == R.id.nav_movies) {
                    mMoviesPagerAdapter = new MoviesPagerAdapter(getSupportFragmentManager());
//                    mMoviesPagerAdapter.clearAll();
//                    mViewPager.removeAllViews();
//                    mViewPager.setAdapter(null);
                    mViewPager.setAdapter(mMoviesPagerAdapter);
                    mViewPager.setOffscreenPageLimit(4);
                    tab.setupWithViewPager(mViewPager);
                    tab.setTabGravity(TabLayout.GRAVITY_CENTER);
                    tab.setTabMode(TabLayout.MODE_SCROLLABLE);
                }
                if(id==R.id.nav_tv){
                    Log.i("TAG", "onNavigationItemSelected: ");
                    mTVPagerAdapter = new TVPagerAdapter(getSupportFragmentManager());
//                    mViewPager.removeAllViews();
//                    mViewPager.setAdapter(null);
//                    mTVPagerAdapter.clearAll();
//                    mViewPager.setAdapter(null);
                    mViewPager.setAdapter(mTVPagerAdapter);
                    mViewPager.setOffscreenPageLimit(4);
                    tab.setupWithViewPager(mViewPager);
                    tab.setTabGravity(TabLayout.GRAVITY_CENTER);
                    tab.setTabMode(TabLayout.MODE_SCROLLABLE);

                }




                return false;
            }

        });
    }















   public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu_file, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.action_account) {
            Intent i=new Intent(this,AccountActivity.class);
            i.putExtra("fullname",fullname);
            startActivity(i);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onListItemClicked(Moviedb m) {
        Intent i=new Intent(MainActivity.this,Main2Activity.class);
        i.putExtra("object",m);
        startActivity(i);

    }

    @Override
    public void onListItemClicked(TV m) {

    }


    public class MoviesPagerAdapter extends FragmentStatePagerAdapter {

FragmentManager fm;
        private ArrayList<Fragment> fragments;
        public MoviesPagerAdapter(FragmentManager fm) {

            super(fm);
            fragments = new ArrayList<>();
            this.fm=fm;
        }
        public void clearAll() //Clear all page
        {
            for (int i = 0; i < fragments.size(); i++) {


                fm.beginTransaction().remove(fragments.get(i)).commit();

            }
            fragments.clear();
        }


        @Override
        public Fragment getItem(int position) {
            if(position==0) {

                Fragmenttab1 fragmenttab1=new Fragmenttab1();
                fragmenttab1.setCoursesFragmentListItemClick(MainActivity.this);
                fragments.add(fragmenttab1);

                return fragmenttab1;
            }
            if(position==1){
                Fragmenttab2 fragmenttab2=new Fragmenttab2();
                fragmenttab2.setCoursesFragmentListItemClick(MainActivity.this);
                fragments.add(fragmenttab2);


                return fragmenttab2;
            }
            if(position==2){
                Fragmenttab3 fragmenttab3=new Fragmenttab3();
                fragmenttab3.setCoursesFragmentListItemClick(MainActivity.this);
                fragments.add(fragmenttab3);

                return fragmenttab3;
            }
            if(position==3){
                Fragmenttab4 fragmenttab4=new Fragmenttab4();
                fragmenttab4.setCoursesFragmentListItemClick(MainActivity.this);
                fragments.add(fragmenttab4);

                return fragmenttab4;
            }


            return null;


        }

        @Override
        public int getCount() {

            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "MOST POPULAR";
                case 1:
                    return "IN THEATRES";
                case 2:
                    return "COMING SOON";
                case 3:
                    return "TOP RATED MOVIES";


            }
            return null;
        }
    }
     public class TVPagerAdapter extends FragmentStatePagerAdapter  {

FragmentManager fm;
ArrayList<Fragment> fragments;
        public TVPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
           this.fm=fm;

        }
        public void clearAll() //Clear all page
        {
            for (int i = 0; i < fragments.size(); i++) {
                fm.beginTransaction().remove(fragments.get(i)).commit();
            }
            fragments.clear();

        }


        @Override
        public Fragment getItem(int position) {
            Log.i("TAG", "getItem: ");
            if(position==0) {
                Fragmenttabtv1 fragmenttabtv1=new Fragmenttabtv1();
                fragmenttabtv1.setCoursesFragmentListItemClick(MainActivity.this);
                Log.i("reachinfragment","aa raha h");
                fragments.add(fragmenttabtv1);
                return fragmenttabtv1;
            }
            if(position==1){
              Fragmenttabtv1 fragmenttabtv1=new Fragmenttabtv1();
                fragmenttabtv1.setCoursesFragmentListItemClick(MainActivity.this);
                fragments.add(fragmenttabtv1);



              return fragmenttabtv1;
            }
            if(position==2){
                Fragmenttabtv1 fragmenttabtv1=new Fragmenttabtv1();
                fragmenttabtv1.setCoursesFragmentListItemClick(MainActivity.this);
                fragments.add(fragmenttabtv1);

                return fragmenttabtv1;

            }
            if(position==3){
                Fragmenttabtv1 fragmenttabtv1=new Fragmenttabtv1();
                fragmenttabtv1.setCoursesFragmentListItemClick(MainActivity.this);
                fragments.add(fragmenttabtv1);

                return fragmenttabtv1;
            }


            return null;


        }

        @Override
        public int getCount() {

            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "AIRING TODAY";
                case 1:
                    return "ON AIR";
                case 2:
                    return "POPULAR";
                case 3:
                    return "TOP RATED" +
                            "" +
                            "";


            }
            return null;
        }
    }



}
