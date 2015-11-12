package bookmanager.chalmers.edu.bookmanager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    final int REQUEST_CODE_CREATE_BOOK = 5;
    SectionsPagerAdapter mSectionsPagerAdapter;

    public SimpleBookManager getManager() {
        return manager;
    }

    private SimpleBookManager manager;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = SimpleBookManager.getInstance();

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.save_key), MODE_PRIVATE);
        String deserialize = sharedPref.getString("books", "");

        if (deserialize.isEmpty()){
            manager.addBook(new Book("Henry", "Test", "1548795623", 15, "TIN093"));
            manager.addBook(new Book("Henry2", "Test2", "15484795623", 20, "TIN093"));
            manager.addBook(new Book("Henry3", "Test3", "154487495623", 25, "TIN093"));
            manager.addBook(new Book("Henry4", "Test4", "154879544623", 30, "TIN093"));
            manager.addBook(new Book("Henry5", "Test5", "15484795623", 35, "TIN093"));
        }
        else{
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Book>>() {}.getType();
            ArrayList<Book> books = gson.fromJson(deserialize, type);

            for (Book b : books){
                SimpleBookManager.getInstance().addBook(b);
            }
        }



        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_book) {
            int requestCode = REQUEST_CODE_CREATE_BOOK;
            Intent intent = new Intent(this, CreateBookActivity.class);
            startActivityForResult(intent, requestCode);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_CODE_CREATE_BOOK) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                Book b = new Book();
                b.setTitle(data.getStringExtra("title"));
                b.setAuthor(data.getStringExtra("author"));
                b.setCourse(data.getStringExtra("course"));
                b.setPrice(Integer.valueOf(data.getStringExtra("price")));
                b.setIsbn(data.getStringExtra("isbn"));
                SimpleBookManager.getInstance().addBook(b);

                ((SummaryFragment) mSectionsPagerAdapter.getItem(0)).updateView();
                ((SummaryFragment)mSectionsPagerAdapter.getItem(1)).updateView();

                Context context = this;
                SharedPreferences sharedPref = context.getSharedPreferences(
                        getString(R.string.save_key), Context.MODE_PRIVATE);

                Gson gson = new Gson();
                String serialize = gson.toJson(SimpleBookManager.getInstance().getAllBooks());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("books", serialize);
                editor.commit();

            }
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        Fragment fragments[] = new Fragment[2];

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            if (position == 0){
                if (fragments[position] == null){
                    fragments[position] = new SummaryFragment(); //CollectionFragment();
                }
                return fragments[position];
            }
            else{
                if (fragments[position] == null){
                    fragments[position] = new SummaryFragment(); //CollectionFragment();
                }
                return fragments[position];
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    /*public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        /*private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        /*public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }*/

}
