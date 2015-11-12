package com.skyblue.taskgenius;

import android.app.Application;
import com.firebase.client.Firebase;

public class FirebaseApp extends Application {

    public Firebase getFirebaseObj() {
        if (firebaseObj == null)
            firebaseObj = new Firebase("https://taskgeniusskyblue.firebaseio.com/");
        return firebaseObj;
    }

    public void setFirebaseObj(Firebase firebaseObj) {
        this.firebaseObj = firebaseObj;
    }

    private Firebase firebaseObj;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        // other setup code
    }


}
