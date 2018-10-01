package database.example.com.realmsampledb;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Mimo Saha on [25-Sep-2018 at 11:03 AM].
 * * Email: mimosaha@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: RealmSampleDB.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [25-Sep-2018 at 11:03 AM].
 * * --> <Second Editor> on [25-Sep-2018 at 11:03 AM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [25-Sep-2018 at 11:03 AM].
 * * --> <Second Reviewer> on [25-Sep-2018 at 11:03 AM].
 * * ============================================================================
 **/
public class Person extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private RealmList<Dog> dogs; // Declare one-to-many relationships

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(RealmList<Dog> dogs) {
        this.dogs = dogs;
    }
}
