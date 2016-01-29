package kalpesh.mac.com.rxandroid_alliantscode.di.components;


import android.content.SharedPreferences;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Component;
import kalpesh.mac.com.rxandroid_alliantscode.di.modules.AppModule;
import kalpesh.mac.com.rxandroid_alliantscode.di.modules.NetModule;
import retrofit.RestAdapter;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed
    RestAdapter retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
}