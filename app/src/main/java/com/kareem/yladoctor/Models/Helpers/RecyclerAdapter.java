/*
 * Copyright (c) $today.year.kareem elsayed aly,no one has the authority to edit,delete,copy any part without my permission
 */

package com.kareem.yladoctor.Models.Helpers;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.kareem.yladoctor.Models.Exceptions.DatabaseErrorNodeException;
import com.kareem.yladoctor.Models.Exceptions.NullNodeException;
import com.kareem.yladoctor.Models.Interfaces.RecyclerAdapterListener;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by kareem on 9/10/2016.
 * <br></br>
 * it's responsible for sitting recycler listener -for recyclerView- to a specific node
 *
 * @author kareem
 * @version %I%, %G%
 * @since 1.1
 */
public class RecyclerAdapter<T, E extends ViewHolder> {
    private RecyclerAdapterListener listeners;

    /**
     * construct and initialize listener to retrieve and send data with it
     *
     * @param listeners is the instance of the class that implements the RecyclerAdapterListener interface
     * @see RecyclerAdapterListener
     * @since 1.1
     */
    public RecyclerAdapter(RecyclerAdapterListener listeners) {
        this.listeners = listeners;
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
    public DatabaseReference getReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    /**
     * create recyclerView listener for a certain node
     *
     * @param recyclerView recycler view to be initialized -set layout,fixed size-
     * @param V            View #Class
     * @param VH           #Class extends ViewHolder
     * @param Layout       layout of each recyclerView Item
     * @param q            #Query to be listened to and grab data according to it
     * @param ID           is the associated ID with this operation-every process get identified by it's assigned ID,passed to RecyclerAdapterListener method-
     * @throws DatabaseErrorNodeException is called whenever isCancelled method is called and error occurs
     * @throws NullNodeException          when path is directing to null Node
     * @see Query ,{@link FirebaseRecyclerAdapter} , {@link RecyclerAdapterListener}
     * @since 1.2
     */
    @SuppressWarnings("unused")
    public void initRecyclerAdapterListener( final Object Data, RecyclerView recyclerView, Class<T> V, Class<E> VH, int Layout, Query q, final String ID) throws NullNodeException {
        recyclerView.setLayoutManager(new LinearLayoutManager(listeners.getMyContext()));
        recyclerView.setHasFixedSize(true);
        FirebaseRecyclerAdapter<T, E> adapter = new FirebaseRecyclerAdapter<T, E>(V, Layout, VH, q) {
            @SuppressWarnings("unchecked")
            @Override
            protected void populateViewHolder(E viewHolder, T model, int position) {
                listeners.recyclerAdapterListener(Data, viewHolder, model, position, ID);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    /**
     * create recyclerView listener for a certain node
     *
     * @param recyclerView recycler view to be initialized -set layout,fixed size-
     * @param V            View #Class
     * @param VH           #Class extends ViewHolder
     * @param Layout       layout of each recyclerView Item
     * @param reference    #DatabaseReference to be listened to and grab data from it
     * @param ID           is the associated ID with this operation-every process get identified by it's assigned ID,passed to RecyclerAdapterListener method-
     * @throws DatabaseErrorNodeException is called whenever isCancelled method is called and error occurs
     * @throws NullNodeException          when path is directing to null Node
     * @see DatabaseReference ,{@link FirebaseRecyclerAdapter} , {@link RecyclerAdapterListener}
     * @since 1.1
     */
    public void initRecyclerAdapterListener( final Object Data, RecyclerView recyclerView, Class<T> V, Class<E> VH, int Layout, DatabaseReference reference, final String ID) {
        recyclerView.setLayoutManager(new LinearLayoutManager(listeners.getMyContext()));
        recyclerView.setHasFixedSize(true);
        FirebaseRecyclerAdapter<T, E> adapter = new FirebaseRecyclerAdapter<T, E>(V, Layout, VH, reference) {
            @Override
            protected void populateViewHolder(E viewHolder, T model, int position) {
                //noinspection unchecked
                listeners.recyclerAdapterListener(Data, viewHolder, model, position, ID);
            }
        };
        recyclerView.setAdapter(adapter);
    }

}
