package kalpesh.mac.com.rxandroid_alliantscode;

import android.app.Application;

import kalpesh.mac.com.rxandroid_alliantscode.constants.Constants;
import kalpesh.mac.com.rxandroid_alliantscode.di.components.APIComponents;
import kalpesh.mac.com.rxandroid_alliantscode.di.components.DaggerAPIComponents;
import kalpesh.mac.com.rxandroid_alliantscode.di.components.DaggerNetComponent;
import kalpesh.mac.com.rxandroid_alliantscode.di.components.NetComponent;
import kalpesh.mac.com.rxandroid_alliantscode.di.modules.APIModule;
import kalpesh.mac.com.rxandroid_alliantscode.di.modules.AppModule;
import kalpesh.mac.com.rxandroid_alliantscode.di.modules.NetModule;

/**
 * Created by kalpesh on 20/01/2016.
 */
public class MyApp extends Application {



   private NetComponent mNetComponent;
   private APIComponents mApiComponents;
    @Override
    public void onCreate() {
        super.onCreate();
        // specify the full namespace of the component
        // Dagger_xxxx (where xxxx = component name)
       mNetComponent= DaggerNetComponent.builder()
               .netModule(new NetModule(Constants.BASE_URL))
               .appModule(new AppModule(this))
               .build();

        mApiComponents= DaggerAPIComponents.builder()
                .netComponent(mNetComponent)
                .aPIModule(new APIModule())
                .build();

    }
    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public APIComponents getApiComponent() {
        return mApiComponents;
    }
}
