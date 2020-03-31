package com.pl.lab4.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskListContent {

    public static final List<Task> ITEMS = new ArrayList<Task>();

    public static final Map<String, Task> ITEM_MAP = new HashMap<String, Task>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Task item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Task createDummyItem(int position) {
        return new Task(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class Task {
        public final String id;
        public final String title;
        public final String details;
        public final String picPath;

        public Task(String id, String title, String details){
            this.id = id;
            this.title = title;
            this.details = details;
            this.picPath = "";
        }

        public Task(String id, String title, String details, String picPath) {
            this.id = id;
            this.title = title;
            this.details = details;
            this.picPath = picPath;
        }


//        @Override
//        public String toString() {
//            return content;
//        }
    }
}
