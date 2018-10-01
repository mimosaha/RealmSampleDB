package database.example.com.realmsampledb;

import io.realm.RealmObject;

/**
 * * ============================================================================
 * * Copyright (C) 2018 W3 Engineers Ltd - All Rights Reserved.
 * * Unauthorized copying of this file, via any medium is strictly prohibited
 * * Proprietary and confidential
 * * ----------------------------------------------------------------------------
 * * Created by: Mimo Saha on [25-Sep-2018 at 10:54 AM].
 * * Email: mimosaha@w3engineers.com
 * * ----------------------------------------------------------------------------
 * * Project: RealmSampleDB.
 * * Code Responsibility: <Purpose of code>
 * * ----------------------------------------------------------------------------
 * * Edited by :
 * * --> <First Editor> on [25-Sep-2018 at 10:54 AM].
 * * --> <Second Editor> on [25-Sep-2018 at 10:54 AM].
 * * ----------------------------------------------------------------------------
 * * Reviewed by :
 * * --> <First Reviewer> on [25-Sep-2018 at 10:54 AM].
 * * --> <Second Reviewer> on [25-Sep-2018 at 10:54 AM].
 * * ============================================================================
 **/
public class Dog extends RealmObject {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public Dog setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Dog setAge(int age) {
        this.age = age;
        return this;
    }

}
