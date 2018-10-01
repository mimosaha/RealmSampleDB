package database.example.com.realmsampledb;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.Random;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.internal.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Realm realm;
    private Button insertOperation, export, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        insertOperation = findViewById(R.id.insert);
        export = findViewById(R.id.export);
        delete = findViewById(R.id.delete);

        insertOperation.setOnClickListener(this);
        delete.setOnClickListener(this);
        export.setOnClickListener(this);

        showOutput();
    }

    private void init() {
        try {
            Random random = new Random();
            Dog dog = new Dog().setName("Rex").setAge(random.nextInt(10));

            realm.beginTransaction();
            final Dog managedDog = realm.copyToRealm(dog); // Persist unmanaged objects
            Person person = realm.createObject(Person.class, random.nextInt(20000)); // Create managed objects directly
            person.getDogs().add(managedDog);
            realm.commitTransaction();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void export() {
        try {
            final File file = new File(Environment.getExternalStorageDirectory().getPath().concat("/sample.realm"));
            if (file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.delete();
            }

            realm.writeCopyTo(file);
            Toast.makeText(MainActivity.this, "Success export realm file", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            realm.close();
            e.printStackTrace();
        }
    }

    private void showOutput() {

        final RealmResults<Dog> puppies = realm.where(Dog.class).lessThan("age", 10).findAll();

        // Listeners will be notified when data changes
        puppies.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Dog>>() {
            @Override
            public void onChange(RealmResults<Dog> results, OrderedCollectionChangeSet changeSet) {
                // Query results are updated in real time with fine grained notifications.
                changeSet.getInsertions(); // => [0] is added.

                for (Dog dog : results) {
                    Log.v("MIMO_SAHA::", "Age: " + dog.getAge());
                }
            }
        });

        // Asynchronously update objects on a background thread
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm bgRealm) {
//                Dog dog = bgRealm.where(Dog.class).equalTo("age", 1).findFirst();
//                dog.setAge(3);
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                // Original queries and Realm objects are automatically updated.
//                puppies.size(); // => 0 because there are no more puppies younger than 2 years old
////                managedDog.getAge();   // => 3 the dogs age is updated
//            }
//        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.insert:
                init();
                break;

            case R.id.export:
                export();
                break;

            case R.id.delete:
                updateData();
                break;
        }
    }

    private void deleteData() {
        realm.beginTransaction();
        RealmResults<Dog> books = realm.where(Dog.class).lessThan("age", 5).findAll();
        if(!books.isEmpty()) {
            for(int i = books.size() - 1; i >= 0; i--) {
                books.deleteAllFromRealm();
            }
        }
        realm.commitTransaction();
    }

    private void updateData() {
        // Asynchronously update objects on a background thread
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Dog dog = bgRealm.where(Dog.class).equalTo("age", 6).findFirst();
                dog.setAge(3);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Original queries and Realm objects are automatically updated.
//                puppies.size(); // => 0 because there are no more puppies younger than 2 years old
//                managedDog.getAge();   // => 3 the dogs age is updated
            }
        });
    }
}
