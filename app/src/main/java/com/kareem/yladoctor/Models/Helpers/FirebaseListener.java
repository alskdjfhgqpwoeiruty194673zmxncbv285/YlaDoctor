/*
 * Copyright (c) $today.year.kareem elsayed aly,no one has the authority to edit,delete,copy any part without my permission
 */

package com.kareem.yladoctor.Models.Helpers;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.kareem.yladoctor.Models.Exceptions.DatabaseErrorNodeException;
import com.kareem.yladoctor.Models.Exceptions.NullNodeException;
import com.kareem.yladoctor.Models.Interfaces.FirebaseListeners;


/**
 * Created by kareem on 9/10/2016.
 * <br></br>
 * it's responsible for sitting listeners -singleTime,multiple- to a specific node
 *
 * @author kareem
 * @version %I%, %G%
 * @since 1.1
 */

public class FirebaseListener {
    private FirebaseListeners listener;

    /**
     * construct and initialize listener to retrieve and send data with it
     *
     * @param listener is the instance of the class that implements the FirebaseListeners interface
     * @see FirebaseListeners
     * @since 1.1
     *
     */
    public FirebaseListener(FirebaseListeners listener) {
        this.listener = listener;
    }

    /**
     * return reference from the FireBase file
     * <br></br>
     * =<code>FireBaseDatabase.getInstance().getReference()</code>
     *
     * @return #DatabaseReference
     * @see DatabaseReference
     * @since 1.1
     */
    public static DatabaseReference getReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    /**
     * create always listening listener for a certain node
     *
     * @param data is the data to be passed through the listener-received to --> from ValueEventListener method,doesn't get affected-
     * @param path is the path to the node-as #String - to set listener to it
     * @param ID   is the associated ID with this operation-every process get identified by it's assigned ID,passed to ValueEventListener method-
     * @throws NullNodeException          when path is directing to null Node
     * @throws DatabaseErrorNodeException is called whenever isCancelled method is called and error occurs
     * @see FirebaseListeners#ValueEventListener(Object, DataSnapshot, String)
     * @since 1.1
     */
    public void initValueEventListener(final Object data, String path, final String ID) {
        getReference().child(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot == null || !dataSnapshot.exists())
//                    listener.onFailure(data, new NullNodeException(), ID);
//                else
                    listener.ValueEventListener(data, dataSnapshot, ID);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure(data, new DatabaseErrorNodeException(databaseError.getMessage()), ID);
            }
        });
    }

    /**
     * create always listening listener for a certain node
     *
     * @param data is the data to be passed through the listener-received to --> from ValueEventListener method,doesn't get affected-
     * @param ref  is the #DatabaseReference to set listener to it
     * @param ID   is the associated ID with this operation-every process get identified by it's assigned ID,passed to ValueEventListener method-
     * @throws NullNodeException          when path is directing to null Node
     * @throws DatabaseErrorNodeException is called whenever isCancelled method is called and error occurs
     * @see FirebaseListeners#ValueEventListener(Object, DataSnapshot, String)
     * @since 1.2
     */
    @SuppressWarnings("unused")
    public void initValueEventListener( final Object data, DatabaseReference ref, final String ID) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot == null || !dataSnapshot.exists())
//                    listener.onFailure(data, new NullNodeException(), ID);
//                else
                    listener.ValueEventListener(data, dataSnapshot, ID);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure(data, new DatabaseErrorNodeException(databaseError.getMessage()), ID);
            }
        });
    }

    /**
     * create always listening listener for a certain node
     *
     * @param data  is the data to be passed through the listener-received to --> from ValueEventListener method,doesn't get affected-
     * @param query is the #Query to set listener to it
     * @param ID    is the associated ID with this operation-every process get identified by it's assigned ID,passed to ValueEventListener method-
     * @throws NullNodeException          when path is directing to null Node
     * @throws DatabaseErrorNodeException is called whenever isCancelled method is called and error occurs
     * @see FirebaseListeners#ValueEventListener(Object, DataSnapshot, String)
     * @see Query
     * @since 1.2
     */
    @SuppressWarnings("unused")
    public void initValueEventListener( final Object data, Query query, final String ID) {
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot == null || !dataSnapshot.exists())
//                    listener.onFailure(data, new NullNodeException(), ID);
//                else
                    listener.ValueEventListener(data, dataSnapshot, ID);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure(data, new DatabaseErrorNodeException(databaseError.getMessage()), ID);
            }
        });
    }

    /**
     * create single listening listener for a certain node
     *
     * @param data is the data to be passed through the listener-received to --> from SingleValueEventListener method,doesn't get affected-
     * @param path is the path to the node-as #String - to set listener to it
     * @param ID   is the associated ID with this operation-every process get identified by it's assigned ID,passed to ValueEventListener method-
     * @throws NullNodeException          when path is directing to null Node
     * @throws DatabaseErrorNodeException is called whenever isCancelled method is called and error occurs
     * @see FirebaseListeners#SingleValueEventListener(Object, DataSnapshot, String)
     * @since 1.1
     */
    public void initSingleValueEventListener(final Object data, String path, final String ID) {
        getReference().child(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot == null || !dataSnapshot.exists())
//                    listener.onFailure(data, new NullNodeException(), ID);
//                else

                    listener.SingleValueEventListener(data, dataSnapshot, ID);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure(data, new DatabaseErrorNodeException(databaseError.getMessage()), ID);
            }
        });
    }

    /**
     * create single listening listener for a certain node
     *
     * @param data is the data to be passed through the listener-received to --> from SingleValueEventListener method,doesn't get affected-
     * @param ref  is the #DatabaseReference to set listener to it
     * @param ID   is the associated ID with this operation-every process get identified by it's assigned ID,passed to ValueEventListener method-
     * @throws NullNodeException          when path is directing to null Node
     * @throws DatabaseErrorNodeException is called whenever isCancelled method is called and error occurs
     * @see FirebaseListeners#SingleValueEventListener(Object, DataSnapshot, String)
     * @since 1.2
     */
    @SuppressWarnings("unused")
    public void initSingleValueEventListener( final Object data, DatabaseReference ref, final String ID) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot == null || !dataSnapshot.exists())
//                    listener.onFailure(data, new NullNodeException(), ID);
//                else
                    listener.SingleValueEventListener(data, dataSnapshot, ID);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure(data, new DatabaseErrorNodeException(databaseError.getMessage()), ID);
            }
        });
    }

    /**
     * create single listening listener for a certain node
     *
     * @param data  is the data to be passed through the listener-received to --> from SingleValueEventListener method,doesn't get affected-
     * @param query is the #Query to set listener to it
     * @param ID    is the associated ID with this operation-every process get identified by it's assigned ID,passed to ValueEventListener method-
     * @throws NullNodeException          when path is directing to null Node
     * @throws DatabaseErrorNodeException is called whenever isCancelled method is called and error occurs
     * @see FirebaseListeners#SingleValueEventListener(Object, DataSnapshot, String)
     * @since 1.2
     */
    public void initSingleValueEventListener( final Object data, Query query, final String ID) {
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot == null || !dataSnapshot.exists())
//                    listener.onFailure(data, new NullNodeException(), ID);
//                else
                    listener.SingleValueEventListener(data, dataSnapshot, ID);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure(data, new DatabaseErrorNodeException(databaseError.getMessage()), ID);

            }
        });
    }
}
