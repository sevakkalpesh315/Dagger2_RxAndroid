package kalpesh.mac.com.rxandroid_alliantscode.di.components;

import dagger.Component;
import kalpesh.mac.com.rxandroid_alliantscode.MainActivity;
import kalpesh.mac.com.rxandroid_alliantscode.di.modules.APIModule;
import kalpesh.mac.com.rxandroid_alliantscode.di.scopes.UserScope;

/**
 * Created by kalpesh on 20/01/2016.
 */

    @UserScope
    @Component(dependencies =NetComponent.class, modules = APIModule.class)
    public interface APIComponents {

    void inject(MainActivity activity);

}
