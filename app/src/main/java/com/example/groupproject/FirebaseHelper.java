package com.example.groupproject;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Source;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * The purpose of this class is to hold ALL the code to communicate with Firebase.  This class
 * will connect with Firebase auth and Firebase firestore.  Each class that needs to verify
 * authentication OR access data from the database will reference a variable of this class and
 * call a method of this class to handle the task.  Essentially this class is like a "gopher" that
 * will go and do whatever the other classes want or need it to do.  This allows us to keep all
 * our other classes clean of the firebase code and also avoid having to update firebase code
 * in many places.  This is MUCH more efficient and less error prone.
 */
public class FirebaseHelper {
    public final String TAG = "GroupProject";
    private static String uid = null;      // var will be updated for currently signed in user
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private double usersCurrency;


    public FirebaseHelper() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        usersCurrency = MainGameActivity.getCryptoCount();
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    /*
    public double getCurrency(){
        db.collection("users").document(mAuth.getUid());
        return
    }
    */

    public void logOutUser() {
        mAuth.signOut();
        this.uid = null;
    }
    public void updateUid(String uid) {
        this.uid = uid;
    }

    public void addUserToFirestore(String name, String newUID) {
        // Create a new user with their name
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("uid", newUID);
        user.put("currency", "" + usersCurrency);
        // Add a new document with a docID = to the authenticated user's UID
        db.collection("users").document(newUID)
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                                Log.d(TAG, name + "'s user account added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding user account", e);
                    }
                });
    }


    public void updateFirebase() {
        usersCurrency = MainGameActivity.getCryptoCount();
        DocumentReference ref = db.collection("users").document(mAuth.getUid());
        //Log.d("Logan", old + " Logging");
        ref.update("currency", usersCurrency + "");
        Log.d("Logan", usersCurrency + " Added to currency");
        if (MainGameActivity.getCryptoCount() == 0) {
            //MainGameActivity.setCryptoCount(Double.parseDouble();
        }
    }



/*
    public interface FirestoreCallback {
        void onCallback(double usersCurrency);
    }
    public void addData(double usersCurrency) {
        // add Memory m to the database
        // this method is overloaded and incorporates the interface to handle the asynch calls
        addData(usersCurrency, new FirestoreCallback() {
            @Override
            public void onCallback(double usersCurrency) {
                Log.i(TAG, "Inside addData, onCallback :" + usersCurrency);
            }

        });
    }


    private void addData(double usersCurrency, FirestoreCallback firestoreCallback) {
        db.collection("users").document(uid).collection("users-currency-amt")
                .add(usersCurrency)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // This will set the docID key for the Memory that was just added.
                        db.collection("users").document(uid).collection("users-currency-amt").
                                document(documentReference.getId()).update("docID", documentReference.getId());
                        Log.i(TAG, "just added " + usersCurrency);
                        readData(firestoreCallback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error adding document", e);
                    }
                });
    }

    private void readData(FirestoreCallback firestoreCallback) {
        usersCurrency = 0;        // empties the AL so that it can get a fresh copy of data
        db.collection("users").document(uid).collection("users-currency-amt")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc: task.getResult()) {
                                int usersCurrency = (int) MainGameActivity.getCryptoCount();
                            }

                            Log.i(TAG, "Success reading data: "+ usersCurrency);
                            firestoreCallback.onCallback(usersCurrency);
                        }
                        else {
                            Log.d(TAG, "Error getting documents: " + task.getException());
                        }
                    }
                });
    }
*/

}

