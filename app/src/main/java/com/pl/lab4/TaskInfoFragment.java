package com.pl.lab4;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pl.lab4.tasks.TaskListContent;

import org.w3c.dom.Text;

public class TaskInfoFragment extends Fragment {

    public TaskInfoFragment() {
        // Required empty public constructor
    }

    public void displayTask(TaskListContent.Task task){
        FragmentActivity activity = getActivity();

        TextView taskInfoTitle = activity.findViewById(R.id.taskInfoTitle);
        TextView taskInfoDescription = activity.findViewById(R.id.taskInfoDescription);
        ImageView taskInfoImage = activity.findViewById(R.id.taskInfoImage);

        taskInfoTitle.setText(task.title);
        taskInfoDescription.setText(task.details);
        if(task.picPath.contains("drawable")){
            Drawable taskDrawable;
            switch (task.picPath) {
                case "drawable 1":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.circle_drawable_green);
                    break;
                case "drawable 2":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.circle_drawable_orange);
                    break;
                case "drawable 3":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.circle_drawable_red);
                    break;
                default:
                    taskDrawable = activity.getResources().getDrawable(R.drawable.circle_drawable_green);
            }
            taskInfoImage.setImageDrawable(taskDrawable);
        } else {
            taskInfoImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.circle_drawable_green));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceStates){
        super.onActivityCreated(savedInstanceStates);
        Intent intent = getActivity().getIntent();
        if(intent != null){
            TaskListContent.Task receivedTask = intent.getParcelableExtra(MainActivity.taskExtra);
            if(receivedTask != null){
                displayTask(receivedTask);
            }
        }
    }
}
