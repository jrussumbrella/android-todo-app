package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> tasks;
    private ArrayAdapter<String> tasksAdapter;
    private ListView listViewTasks;
    private Button btnAdd;
    private EditText editTextTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTasks = findViewById(R.id.listViewTasks);
        btnAdd = findViewById(R.id.btnAdd);
        editTextTask = findViewById(R.id.editTextTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTodo(view);
            }
        });

        tasks = new ArrayList<>();
        tasksAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listViewTasks.setAdapter(tasksAdapter);

        setupTaskListViewListener();
    }

    private void setupTaskListViewListener() {
        listViewTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                tasks.remove(position);
                tasksAdapter.notifyDataSetChanged();
                Toast.makeText(context, "Task removed", Toast.LENGTH_LONG).show();
                return  true;
            }
        });
    }

    private void addTodo(View view) {
        String taskText = editTextTask.getText().toString();
        Context context = getApplicationContext();
        if(taskText.equals("")) {
            Toast.makeText(context, "Task cannot be empty!", Toast.LENGTH_LONG).show();
            return;
        }
        tasksAdapter.add(taskText);
        editTextTask.setText("");
        editTextTask.onEditorAction(EditorInfo.IME_ACTION_DONE);
        Toast.makeText(context, "You have successfully added a task", Toast.LENGTH_LONG).show();
    }


}