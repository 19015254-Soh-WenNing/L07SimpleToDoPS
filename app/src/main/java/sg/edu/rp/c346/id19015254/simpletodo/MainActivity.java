package sg.edu.rp.c346.id19015254.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etText;
    Button btnAdd, btnClear, btnDelete;
    ListView lvToDo;
    Spinner spnAddRemove;
    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etText);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);
        lvToDo = findViewById(R.id.lvToDo);
        spnAddRemove = findViewById(R.id.spnAddRemove);

        alToDo = new ArrayList<String>();

        aaToDo = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, alToDo);
        lvToDo.setAdapter(aaToDo);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etText.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        etText.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etText.getText().toString();
                etText.setText("");
                alToDo.add(task);
                aaToDo.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alToDo.size() == 0)
                {
                    Toast.makeText(getBaseContext(),"You don't have any tasks to remove",Toast.LENGTH_SHORT).show();
                    etText.setText("");
                }
                else
                {
                    int index = Integer.parseInt(etText.getText().toString());
                    if (index + 1 > alToDo.size())
                    {
                        Toast.makeText(getBaseContext(),"Wrong index number",Toast.LENGTH_SHORT).show();
                        etText.setText("");
                    }
                    else
                    {
                        etText.setText("");
                        alToDo.remove(index);
                        aaToDo.notifyDataSetChanged();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });
    }
}