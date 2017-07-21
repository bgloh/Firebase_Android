package com.example.bgloh.firebasetrial4;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    DatabaseReference mPostReference;
    Person person1 = new Person();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mSave = (Button)findViewById(R.id.SaveButton);


        // Initialize Database
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("message").child("person1");

        //Button Callback
       mSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // Initialization
               EditText mName = (EditText) findViewById(R.id.NameEditText);
               EditText mAddress = (EditText) findViewById(R.id.AddressEditText);
               person1.setName(mName.getText().toString());
               person1.setAddress(mAddress.getText().toString());

               // Write a message to the database
               FirebaseDatabase database = FirebaseDatabase.getInstance();
               DatabaseReference myRef = database.getReference().child("message").child("person1");
               myRef.setValue(person1);

               Log.d("firebaseD", person1.getName());

           }
       }
    );
}

    @Override
    public void onStart() {
        super.onStart();
        //



        // Read from the database
        mPostReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                TextView mPerson = (TextView) findViewById(R.id.PersonTextView);
                Person data = dataSnapshot.getValue(Person.class);
                String result = "name: " + data.getName() + ", address: " + data.getAddress();
                mPerson.setText(result);
                Log.d("firebaseD", "Value is: " + data.toString());
                Log.d("firebaseD", "i'm called !!.");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebaseD", "Failed to read value.", error.toException());
            }
        });
        //
    }

    // Custom Class



    //


}
