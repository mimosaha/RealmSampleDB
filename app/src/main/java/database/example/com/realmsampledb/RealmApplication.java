package database.example.com.realmsampledb;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Mimo Saha on [25-Sep-2018 at 10:38 AM].
 * * Email: mimosaha@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: RealmSampleDB.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [25-Sep-2018 at 10:38 AM].
 * * --> <Second Editor> on [25-Sep-2018 at 10:38 AM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [25-Sep-2018 at 10:38 AM].
 * * --> <Second Reviewer> on [25-Sep-2018 at 10:38 AM].
 * * ============================================================================
 **/
public class RealmApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        Realm.init(context);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("sample.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    public static Context getAppContext() {
        return context;
    }
}
