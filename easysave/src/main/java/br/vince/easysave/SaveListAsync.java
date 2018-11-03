package br.vince.easysave;

import android.os.AsyncTask;

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

class SaveListAsync<T> extends AsyncTask<Void, Void, Boolean> {

    private final EasySave mEasySave;
    private final List<T> mData;
    private final String mTag;
    private final SaveAsyncCallback<List<T>> mSaveListener;

    public SaveListAsync(final EasySave easySave, final List<T> data, final String tag, final SaveAsyncCallback<List<T>> saveListener) {
        mEasySave = easySave;
        mData = data;
        mTag = tag;
        mSaveListener = saveListener;
    }

    @Override
    protected Boolean doInBackground(final Void... voids) {

        try {

            mEasySave.saveList(mTag, mData);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    protected void onPostExecute(final Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        if (aBoolean) {
            mSaveListener.onComplete(mData);
        } else {
            mSaveListener.onError("Error saving data");
        }

    }
}
