package com.example.avma1997.tmdb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity implements Fragtab1.MoviesFragmentListItemClicked {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ViewPager mImageViewPager;
    Moviedb m;
    FloatingActionButton fabplus,fabrating,fabreview,fabreminder,fabfavourite,fabwatchlist;
    Animation fabOpen,fabClose,rotateCW,rotateACW;
    boolean isOpen=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container2);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        TabLayout tab = (TabLayout) findViewById(R.id.tab2);
        tab.setupWithViewPager(mViewPager);
        Intent i = getIntent();
        m = (Moviedb) i.getSerializableExtra("object");
        getSupportActionBar().setTitle(m.title);
        final ImageView iv1 = (ImageView) findViewById(R.id.iv1);
       final ImageView iv2 = (ImageView) findViewById(R.id.iv2);
       final ImageView iv3 = (ImageView) findViewById(R.id.iv3);
       final ImageView iv4 = (ImageView) findViewById(R.id.iv4);
      final  ImageView iv5 = (ImageView) findViewById(R.id.iv5);

        int ID = m.id;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<MoviesImageResponse> call = apiInterface.getMovieImages(ID,"69a57d4b7c3b085d539f991b8a27ebce");
        // This is occuring on a different thread and is asynchronous->not in sequence
        call.enqueue(new Callback<MoviesImageResponse>() {
            public void onResponse(Call<MoviesImageResponse> call, Response<MoviesImageResponse> response) {

                MoviesImageResponse movieResponse = response.body();
                ArrayList<Movieimage> movieimageList = movieResponse.getImageList();

                Picasso.with(Main2Activity.this).load("http://image.tmdb.org/t/p/original"+movieimageList.get(0).file_path).into(iv1);
                Picasso.with(Main2Activity.this).load("http://image.tmdb.org/t/p/original"+movieimageList.get(1).file_path).into(iv2);
                Picasso.with(Main2Activity.this).load("http://image.tmdb.org/t/p/original"+movieimageList.get(2).file_path).into(iv3);
                Picasso.with(Main2Activity.this).load("http://image.tmdb.org/t/p/original"+movieimageList.get(3).file_path).into(iv4);
                Picasso.with(Main2Activity.this).load("http://image.tmdb.org/t/p/original"+movieimageList.get(4).file_path).into(iv5);





            }





            @Override
            public void onFailure(Call<MoviesImageResponse> call, Throwable t) {

            }
        });













         fabplus = (FloatingActionButton) findViewById(R.id.fab_plus);

        fabfavourite = (FloatingActionButton) findViewById(R.id.fab_favourite);
        fabwatchlist = (FloatingActionButton) findViewById(R.id.fab_watchlist);
        fabreminder = (FloatingActionButton) findViewById(R.id.fab_reminder);

        fabOpen= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fabClose= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotateCW= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        rotateACW= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);



        fabplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(isOpen)
               {

                   fabwatchlist.startAnimation(fabClose);
                   fabfavourite.startAnimation(fabClose);
                   fabreminder.startAnimation(fabClose);

                   fabplus.startAnimation(rotateACW);
                   fabwatchlist.setClickable(false);
                   fabfavourite.setClickable(false);
                   fabreminder.setClickable(false);

                   isOpen=false;


               }
               else
               {

                   fabwatchlist.startAnimation(fabOpen);
                    fabfavourite.startAnimation(fabOpen);
                   fabreminder.startAnimation(fabOpen);

                   fabplus.startAnimation(rotateCW);

                   fabwatchlist.setClickable(true);
                   fabfavourite.setClickable(true);
                   fabreminder.setClickable(true);
                   isOpen=true;

               }
            }
        });
fabfavourite.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Snackbar.make(v, m.title + " Added to Favourites", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        MovieDatabase db = MovieDatabase.getInstanceFav(Main2Activity.this);
        final MovieDao movieDao = db.movieDao();
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                movieDao.insertAll(m);
                return null;
            }

        }.execute();
    }
});

        fabwatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, m.title + " Added to WatchList", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                MovieDatabase db = MovieDatabase.getInstanceWatch(Main2Activity.this);
                final MovieDao movieDao = db.movieDao();
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        movieDao.insertAll(m);
                        return null;
                    }

                }.execute();
            }
        });









    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClicked(Videos v) {

    }

    @Override
    public void onListItemSelected(Moviedb m) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                Fragtab1 fragtab1 = new Fragtab1();
                fragtab1.setCoursesFragmentListItemClicked(Main2Activity.this);
                Bundle args = new Bundle();
                args.putSerializable("obj", m);
                fragtab1.setArguments(args);
                return fragtab1;
            }
            if(position==1)
            {
                Fragtab2 fragtab2 = new Fragtab2();
                Bundle args = new Bundle();
                args.putSerializable("obj2", m);
                fragtab2.setArguments(args);
                return fragtab2;
            }
            if(position==2)
            {
                Fragtab3 fragtab3 = new Fragtab3();
                Bundle args = new Bundle();
                args.putSerializable("obj3", m);
                fragtab3.setArguments(args);
                return fragtab3;
            }


            return null;
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "INFO";
                case 1:
                    return "CAST";
                case 2:
                    return "REVIEWS";
            }
            return null;
        }
    }
}
