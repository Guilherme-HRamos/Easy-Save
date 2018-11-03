package br.vince.easysave;

import android.os.AsyncTask;

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
class LoadListAsync<T> extends AsyncTask<Void, Void, List<T>> {

    private final EasySave mEasySave;
    private Class<T[]> mType;
    private final String mTag;
    private final LoadAsyncCallback<List<T>> mCallback;

    LoadListAsync(final EasySave easySave, final Class<T[]> type, final String tag, final LoadAsyncCallback<List<T>> callback) {
        mEasySave = easySave;
        mType = type;
        mTag = tag;
        mCallback = callback;
    }

    @Override
    protected List<T> doInBackground(final Void... voids) {
        return loadList();
    }

    private List<T> loadList() {
        return mEasySave.retrieveList(mTag, mType);
    }

    @Override
    protected void onPostExecute(final List<T> ts) {
        super.onPostExecute(ts);
        mCallback.onComplete(new ArrayList<T>(ts));
    }
}
