package com.example.hallbook.hallbooking.fcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.example.hallbook.hallbooking.R;
import com.example.hallbook.hallbooking.entity.Owner;
import com.example.hallbook.hallbooking.entity.Owners;
import com.jakewharton.rxbinding2.widget.RxSearchView;
import com.jakewharton.rxbinding2.widget.SearchViewQueryTextEvent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ARUN SUTHAR on 17-10-2017.
 */

public class OwnersListActivity extends AppCompatActivity implements Callback<Owners> {

    private ArrayList<Owner> ownerList = new ArrayList();
    private RecyclerView recyclerView;
    private OwnersAdapter ownersAdapter;
    private ProgressBar mProgressBar;
    public static String RESTART_ACTION = "restart_action";
    private SearchView mSearchView;
    private Disposable mDisposable;
    private ArrayList<Owner> originalList = new ArrayList<>();
    private String mQuery;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halllist);
        recyclerView = (RecyclerView) findViewById(R.id.halllistview);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        ownersAdapter = new OwnersAdapter(ownerList ,this);
        mSearchView = (SearchView) findViewById(R.id.search_view);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setQueryHint("Search by State,City");

       // fab = (FloatingActionButton) findViewById(R.id.fab);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ownersAdapter);
        mProgressBar.setVisibility(View.VISIBLE);
        prepareBookData();

       /* fab.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view){
                if (PreferenceUtils.isLoggedIn(BooksListActivity.this)) {
                    Intent intent = new Intent(BooksListActivity.this, BookPostActivity.class);
                    startActivity(intent);
                } else {
                    Utils.showToast(BooksListActivity.this, "Please register to add a book");
                }
            }

        });*/
        mDisposable = RxSearchView.queryTextChangeEvents(mSearchView)
                .debounce(400, TimeUnit.MILLISECONDS) // default Scheduler is Computation
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(_getSearchObserver());
    }

    private DisposableObserver<SearchViewQueryTextEvent> _getSearchObserver() {
        return new DisposableObserver<SearchViewQueryTextEvent>() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(SearchViewQueryTextEvent onTextChangeEvent) {
                String query = onTextChangeEvent.queryText().toString();
                searchBooksByNameAndAuthor(query);
            }
        };
    }

    private void searchBooksByNameAndAuthor(String query) {
        if(!TextUtils.isEmpty(query) && originalList.size() > 0) {
            query = query.toLowerCase();
            mQuery = query;
            ownerList.clear();
            for (Owner owner : originalList) {
                if ((owner.getOwnerstate().toLowerCase().contains(query)) || owner.getOwnercity().toLowerCase().contains(query)) {
                    ownerList.add(owner);
                }
            }
        } else {
            mQuery = null;
            ownerList.clear();
            ownerList.addAll(originalList);
        }
        ownersAdapter.notifyDataChanged(mQuery);
    }

    private void prepareBookData()
    {   GetOwnerInterface ownerInterface = HallBookingApplication.getInstance().getRetrofit().
            create(GetOwnerInterface.class);
        Call<Owners> callBooks = ownerInterface.getOwners();
        callBooks.enqueue(OwnersListActivity.this);
    }

    @Override
    public void onResponse(Call<Owners> call, Response<Owners> response) {
        if (Utils.isActivityAlive(OwnersListActivity.this)) {
            if (response.isSuccessful()) {
                Owners ownersPresent = response.body();
                ownerList.clear();
                originalList.clear();
                originalList.addAll(ownersPresent.owners);
                ownerList.addAll(ownersPresent.owners);
                mProgressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                ownersAdapter.notifyDataChanged(mQuery);
            } else {
                Utils.showToast(OwnersListActivity.this, "Something went wrong");
            }
        }
    }

    @Override
    public void onFailure(Call<Owners> call, Throwable t) {
        mProgressBar.setVisibility(View.GONE);
        Utils.showToast(OwnersListActivity.this, "Something went wrong");
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            if(RESTART_ACTION.equals(intent.getAction())) {
                recyclerView.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
                prepareBookData();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hall_list, menu);
        if (PreferenceUtils.isLoggedIn(this)) {
            MenuItem registerItem  = menu.findItem(R.id.register);
            registerItem.setTitle(PreferenceUtils.getStringPrefs(this, PreferenceUtils
                    .SAVED_USER_NAME));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.register:
                if (!PreferenceUtils.isLoggedIn(this)) {
                    startActivity(new Intent(this, OwnerRegisterActivity.class));
                } else {
                    Utils.showToast(this, "Hello "+ PreferenceUtils.getStringPrefs(this,
                            PreferenceUtils.SAVED_OWNER_NAME));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null){
            mDisposable.dispose();
        }
    }




    }










