package kalpesh.mac.com.rxandroid_alliantscode.di.modules;


import dagger.Module;
import dagger.Provides;
import kalpesh.mac.com.rxandroid_alliantscode.di.scopes.UserScope;
import kalpesh.mac.com.rxandroid_alliantscode.observables.IContactsAPI;
import retrofit.RestAdapter;

@Module
public class APIModule {

    @Provides
    @UserScope
    public IContactsAPI providesIContactsInterface(RestAdapter retrofit) {
        return retrofit.create(IContactsAPI.class);
    }
}
