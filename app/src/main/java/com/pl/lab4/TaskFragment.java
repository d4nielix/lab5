package com.pl.lab4;

import android.content.Context;
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

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments() != null) {
//            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager((new LinearLayoutManager(context)));
            myRecyclerViewAdapter = new MyTaskRecyclerViewAdapter(TaskListContent.ITEMS, mListener);
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
        // TODO: Update argument type and name
        void OnListFragmentClickInteraction(TaskListContent.Task task, int position);
        void OnListFragmentLongClickInteraction(int position);
    }
}
