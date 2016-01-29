package kalpesh.mac.com.rxandroid_alliantscode;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import javax.inject.Inject;

import kalpesh.mac.com.rxandroid_alliantscode.adapter.ContactsAdapter;
import kalpesh.mac.com.rxandroid_alliantscode.model.Contacts_model;
import kalpesh.mac.com.rxandroid_alliantscode.observables.IContactsAPI;
import kalpesh.mac.com.rxandroid_alliantscode.utilities.RxUtils;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
    @Inject
    IContactsAPI mIContactsInterface;

    @Inject
    SharedPreferences mSharedPreferences;

    @Inject
    OkHttpClient mOkHttpClient;
    //Composite Subscription
    private IContactsAPI _api;

    private RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;
    private ProgressDialog pDialog;

    /**
     * Subscription that represents a group of Subscriptions that are unsubscribed together.
     */
    private CompositeSubscription _subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);


      //  _api= Services._createCakehubApi();
        ((MyApp) getApplication()).getApiComponent().inject(this);

       pattern();
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onResume() {
        super.onResume();
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);
    }

    @Override
    public void onPause() {
        super.onPause();
        RxUtils.unsubscribeIfNotNull(_subscriptions);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void pattern(){

        //Observable<List> localContacts=
         //       _api.getContacts()
           //     .toList();
        _subscriptions.add(mIContactsInterface.getContacts()

                .observeOn(AndroidSchedulers.mainThread())// Handle on UI Thread
                .subscribeOn(Schedulers.io())//Work on separate thread pool
                .subscribe(new Observer<List<Contacts_model>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("Retrofit", "onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Retrofit", "onError");

                    }

                    @Override
                    public void onNext(List<Contacts_model> contacts_models) {
                        //Like Async PostExecute method

                        Log.i("Retrofit", "onNext");
                        if (contacts_models.size() > 0 && mRecyclerView != null) {
                            mAdapter = new ContactsAdapter(contacts_models, R.layout.card_row, getApplicationContext());
                            mRecyclerView.setAdapter(mAdapter);
                            hidePDialog();
                        }

                    }
                }));

    }
    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
