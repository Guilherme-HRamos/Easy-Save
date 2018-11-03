package br.vince.easysave;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.vince.easysave.EasySaveUtil;

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

public class EasySave extends EasySaveUtil {
    private final Context context;

    public EasySave(Context context) {
        this.context = context;
    }

    public <T> void saveModelAsync(final String key, final T objectModel,  SaveAsyncCallback<T> callback) {
        new SaveAsync<T>(this, objectModel, key, callback).execute();
    }

    public <T> void saveListAsync(final String key, final List<T> list,  SaveAsyncCallback<List<T>> callback) {
        new SaveListAsync<T>(this, list, key, callback).execute();
    }

    public <T> void retrieveModelAsync(String key, Class<T> objectModel, LoadAsyncCallback<T> callback) {
        new LoadObjectAsync<T>(this, objectModel, key, callback).execute();
    }

    public <T> void retrieveListAsync(String key, Class<T[]> objectModel, LoadAsyncCallback<List<T>> callback) {
        new LoadListAsync<T>(this, objectModel, key, callback).execute();
    }

    /**
     * Retrieving a list of data
     * @param key - Like saving preferences, this String is a key to save and get specific data
     * @param model - Base Class that you want to recover. For example, if your List is something like List<String>, you will need put here String[].class
     * @return the saved list, or {@code null} if the list does not exists
     */
    public <T> List<T> retrieveList(String key, Class<T[]> model) {

        T[] obj;

        String modelString = EasySaveUtil.retrieveData(key, context);


        obj = new Gson().fromJson(modelString, model);


        if (obj == null)
            return new ArrayList<>();
        else
            return Arrays.asList(obj);
    }

    /**
     * Saving a list of data
     * @param key - Like saving preferences, this String is a key to save and get specific data
     * @param list - List of content you want
     */
    public <T> void saveList(String key, List<T> list) {
        EasySaveUtil.saveData(key, list, context);
    }

    /**
     * Retrieving only one object
     * @param key - Like saving preferences, this String is a key to save and get specific data
     * @param objectModel - Base Class that you want to recover. For example String.class, or MyObject.class
     * @return the saved object, or {@code null} if the object does not exists
     */
    public <T> T retrieveModel(String key, Class<T> objectModel) {
        String modelString = EasySaveUtil.retrieveData(key, context);
        Object model = null;
        try {
            model = new Gson().fromJson(modelString, objectModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Primitives.wrap(objectModel).cast(model);
    }

    /**
     * Saving a list of data
     * @param key - Like saving preferences, this String is a key to save and get specific data
     * @param objectModel - Object you want to save
     */
    public Object saveModel(String key, Object objectModel) {
        return EasySaveUtil.saveData(key, objectModel, context);
    }
}
