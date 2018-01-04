package br.vince.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.vince.easysave.EasySave;

/*
Copyright 2018 Guilherme Ramos

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

/**
 * Guilherme Ramos.
 * Classe criada em 2018
 * guilhermeramos.dev@gmail.com
 */


public class MainActivity extends AppCompatActivity implements Contract.ContractView {

    private NotesAdapter notesAdapter;
    private EditText editText;
    private EasySave easySave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easySave = new EasySave(this);

        notesAdapter = new NotesAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notesAdapter);

        editText = findViewById(R.id.main_input);

        Button addItem = findViewById(R.id.main_add_item_button);
        Button saveItem = findViewById(R.id.main_save_item_button);
        Button saveAll = findViewById(R.id.main_save_all_button);
        Button restoreOne = findViewById(R.id.main_restore_item_button);

        addItem.setOnClickListener(clicks);
        saveItem.setOnClickListener(clicks);
        saveAll.setOnClickListener(clicks);
        restoreOne.setOnClickListener(clicks);
    }

    public void addNote() {

        String note = editText.getText().toString();

        NoteModel noteModel = new NoteModel();

        noteModel.setNote(note);
        noteModel.setId(notesAdapter.getItemCount() + 1);

        notesAdapter.onAddItem(noteModel);
    }

    /**
     * Saving a list
     */
    private void saveNotes() {
        easySave.saveList("chave2", notesAdapter.onRequestList());
    }

    private void saveOneNote() {
        EditText editText = findViewById(R.id.main_input);

        String note = editText.getText().toString();

        NoteModel noteModel = new NoteModel();

        noteModel.setNote(note);

        // Saving a model, only one object
        easySave.saveModel("modelo", noteModel);
    }

    private void restoreOneNote() {

        // Getting a model, only one object
        NoteModel noteModel = easySave.retrieveModel("modelo", NoteModel.class);

        Toast.makeText(this, "Content: " + noteModel.getNote(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<NoteModel> onRestoreList() {
        // Getting a list
        return easySave.retrieveList("chave2", NoteModel[].class);
    }

    private View.OnClickListener clicks = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.main_add_item_button:
                    addNote();
                    break;
                case R.id.main_save_all_button:
                    saveNotes();
                    break;
                case R.id.main_save_item_button:
                    saveOneNote();
                    break;
                case R.id.main_restore_item_button:
                    restoreOneNote();
                    break;
            }
        }
    };
}
