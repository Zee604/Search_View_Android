package com.saifi369.searchviewdemo.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saifi369.searchviewdemo.R;
import com.saifi369.searchviewdemo.data.MyDataAdapter;
import com.saifi369.searchviewdemo.model.CityDataItem;
import com.saifi369.searchviewdemo.sample.SampleDataProvider;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "mytag";
    private List<CityDataItem> mDataList;
    private RecyclerView mRecyclerView;
    private MyDataAdapter mDataAdapter;
    Button button;
    public static String preference = "com.zee.savedData";
    private boolean stateUsed;
    String id;
   private CityDataItem cityDataItem=new CityDataItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataList = SampleDataProvider.cityDataItemList;
        mRecyclerView = findViewById(R.id.list_city);
//        button=(Button)findViewById(R.id.btn);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        mDataAdapter= new MyDataAdapter(mDataList, this);
        mRecyclerView.setAdapter(mDataAdapter);

        stateUsed = false;


//        init();


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                loadData();
//            }
//        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.app_bar_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.search_view);
        SearchView searchView= (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mDataAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /** this will be called when you switch to other app (or leaves it without closing) */
    @Override
    protected void onPause() {
        super.onPause();
        // pauze tasks
    }

    /** this will be called when you returns back to this app after going into pause state */
    @Override
    protected void onResume() {
        super.onResume();
        // resume tasks
    }

    /** this starts when app closes, but BEFORE onDestroy() */
    // please remember field "savedInstanceState", which will be stored in the memory after this method
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // save the tutorial page (or something else)
        savedInstanceState.putString("id", cityDataItem.getCityName());
        savedInstanceState.putBoolean("stateUsed", stateUsed);
        // more additions possible
    }

    /** this starts after onStart(). After this method, onCreate(Bundle b) gets invoked, followed by onPostCreate(Bundle b) method
     * When this method has ended, the app starts skipping the onCreate and onPostCreate method and initiates then.
     * -- *%* A best practice is to add an init() method which have all startup functions.
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // restore state
        String id = savedInstanceState.getString("id");
        stateUsed = savedInstanceState.getBoolean("stateUsed");
        cityDataItem.setCityName(id);
//        init();
    }

//    /** do your startup tasks here */
//    public void init() {
//        if (!stateUsed) {
//            showTutorial(id);
//        }


}
