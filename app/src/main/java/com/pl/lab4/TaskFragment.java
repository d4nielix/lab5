package com.pl.lab4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pl.lab4.tasks.TaskListContent;

public class TaskFragment extends Fragment {
    private MyTaskRecyclerViewAdapter myRecyclerViewAdapter;

    private static final String ARG_COLUMN_COUNT = "column-count";

    private OnListFragmentClickInteractionListener mListener;

    public TaskFragment() {
    }

    public static TaskFragment newInstance(int columnCount) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager((new LinearLayoutManager(context)));
            myRecyclerViewAdapter = new MyTaskRecyclerViewAdapter(TaskListContent.ITEMS, mListener);
            recyclerView.setAdapter(myRecyclerViewAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentClickInteractionListener) {
            mListener = (OnListFragmentClickInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void notifyDataChange(){
        myRecyclerViewAdapter.notifyDataSetChanged();
    }

    public interface OnListFragmentClickInteractionListener {
        void OnListFragmentClickInteraction(TaskListContent.Task task, int position);
        void OnListFragmentLongClickInteraction(int position);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK){
            if(data != null){
                boolean changeDataSet = data.getBooleanExtra(TaskInfoActivity.DATA_CHANGED_KEY, false);
                if(changeDataSet)
                    notifyDataChange();
            }
        }
    }
}
