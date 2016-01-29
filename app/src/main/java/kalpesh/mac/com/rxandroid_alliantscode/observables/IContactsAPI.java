package kalpesh.mac.com.rxandroid_alliantscode.observables;

import java.util.List;

import kalpesh.mac.com.rxandroid_alliantscode.constants.Constants;
import kalpesh.mac.com.rxandroid_alliantscode.model.Contacts_model;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by kalpesh on 23/12/2015.
 */
public interface IContactsAPI {
    @GET(Constants.GET_CONTACTS_URL)
    Observable<List<Contacts_model>> getContacts();
}
