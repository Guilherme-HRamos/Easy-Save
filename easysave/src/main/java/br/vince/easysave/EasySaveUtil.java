package br.vince.easysave;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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


public class EasySaveUtil {

    /**
     * Salvar o dado que você precisa
     * @param key chave para salvar e recuperar o dado
     * @param json Objeto que você vai salvar (precisa estar no formato JSON)
     * @param context contexto
     * @return true -> ok / false -> Não foi possível salvar
     */
    protected static boolean saveData(String key, Object json, Context context) {
        return writeObject(context, key, new Gson().toJson(json));
    }

    /**
     * Obter algum dado salvo anteriormente
     * @param key chave para salvar e recuperar o dado
     * @param context contexto
     * @return em {@code String} o JSON salvo na memória
     */
    protected static String retrieveData(String key, Context context) {
        return String.valueOf(readObject(context, key));
    }

    private static boolean writeObject(Context context, String key, String object) {
        try {
            //salvando o objeto na memória do Android
            FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false; // caso não seja possível salvar, retornar falso
        }
        return true; // caso seja possível salvar, retornar verdadeiro
    }

    private static Object readObject(Context context, String key) {
        try {
            // Abrindo o "arquivo" criado
            FileInputStream fis = context.openFileInput(key);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return ois.readObject(); // retornando o objeto

        } catch (Exception e) {
            e.printStackTrace();
            return null; // retornar nulo caso não exista ainda arquivo salvo
        }
    }
}
