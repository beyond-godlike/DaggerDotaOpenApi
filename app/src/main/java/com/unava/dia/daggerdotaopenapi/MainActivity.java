package com.unava.dia.daggerdotaopenapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.unava.dia.daggerdotaopenapi.data.APIInterface;
import com.unava.dia.daggerdotaopenapi.data.PlayerMatches;
import com.unava.dia.daggerdotaopenapi.ui.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;

    @Inject
    Retrofit retrofit; // auto inject from net module
    String accId = "131519220";

    public List<PlayerMatches> list;
    public List<String> matchesInfoStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        list = new ArrayList<>();
        matchesInfoStrings = new ArrayList<>();

        ((App) getApplication()).getNetComponent().inject(this);
    }

    public void onFindClick(View view) {
        getPlayerMatch(accId);
    }

    public void getPlayerMatch(String accId) {
        Observable<List<PlayerMatches>> call = retrofit.create(APIInterface.class).getPlayerMatches(accId);

        Observer<List<PlayerMatches>> observer = new Observer<List<PlayerMatches>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<PlayerMatches> playerMatches) {
                list = playerMatches;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                for (PlayerMatches temp : list) {
                    matchesInfoStrings.add(temp.toString());
                }

                // пихаем в адаптер
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, matchesInfoStrings);

                listView.setAdapter(adapter);
            }
        };

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
