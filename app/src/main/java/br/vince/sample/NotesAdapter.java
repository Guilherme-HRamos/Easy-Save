package br.vince.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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

class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> implements Contract.ContractAdapter{

    private final Contract.ContractView contractView;
    private final List<NoteModel> mNotes = new ArrayList<>();

    NotesAdapter(Contract.ContractView contractView) {
        this.contractView = contractView;
        mNotes.addAll(contractView.onRestoreList());
    }


    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, int position) {
        holder.bindItem(mNotes.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    @Override
    public void onAddList(List<NoteModel> models) {
        mNotes.clear();
        mNotes.addAll(models);
        notifyDataSetChanged();
    }

    @Override
    public void onAddItem(NoteModel model) {
        mNotes.add(model);
        notifyItemInserted(mNotes.size() - 1);
    }

    @Override
    public List<NoteModel> onRequestList() {
        return mNotes;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewItem;

        ViewHolder(View itemView) {
            super(itemView);
            textViewItem = itemView.findViewById(R.id.item_list_text);
        }

        void bindItem(String content) {
            textViewItem.setText(content);
        }
    }
}
