# Easy Save

[![](https://jitpack.io/v/Guilherme-HRamos/Easy-Save.svg)](https://jitpack.io/#Guilherme-HRamos/Easy-Save)


Now it's very easy to save lists and objects!
*note: this is my first lib, so be patience with mistakes, please*


## Examples

Assuming a model called `UserPrefs`:
```

public class UserPrefs {

    private String themeColor;
    private String soundOptionsURI;
    private boolean isShownTutorial;
    private int age;

    // all getter and setters below

    public String getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }

    public String getSoundOptionsURI() {
        return soundOptionsURI;
    }

    public void setSoundOptionsURI(String soundOptionsURI) {
        this.soundOptionsURI = soundOptionsURI;
    }
    
    ...

}

```

So now we have our model, we can save or retrieve the data

###### Save an object

```
UserPrefs userPreferences = new UserPrefs();
new EasySave(context).saveModel("my key", userPreferences);

//or, u can do async
new EasySave(context).saveModelAsync("my key", userPreferences, new SaveAsyncCallback<UserPrefs>() {
                    @Override
                    public void onComplete(final UserPrefs data) {
		        // data saved
                        doWhatever(data);
                    }

                    @Override
                    public void onError(final String message) {
                        showMessage(message);
                    }
                });
```

###### Retrieve an object

```
UserPrefs userPreferences = new EasySave(context).retrieveModel("my key", UserPrefs.class);

//or, u can do async
new EasySave(context)
                .retrieveModelAsync("my key", UserPrefs.class, new LoadAsyncCallback<UserPrefs>() {
                    @Override
                    public void onComplete(final UserPrefs data) {

                        doWhatever(data);


                    }

                    @Override
                    public void onError(final String message) {
                        showMessage(message);
                    }
                });
```
###### Save a list of objects

```
List<UserPrefs> myList= new ArrayList<>();

myList.add(user1);
myList.add(user2);
...

new EasySave(context).saveList("my keys", myList);

//or u can do async
new EasySave(context)
                .saveListAsync("my keys", myList, new SaveAsyncCallback<List<UserPrefs>>() {
                    @Override
                    public void onComplete(final List<UserPrefs> data) {
                        doWhatever(data);
                    }

                    @Override
                    public void onError(final String message) {
                        showMessage(message);
                    }
                });
```

###### Retrieve a list of objects

```
List<UserPrefs> myList = new EasySave(context).retrieveList("my keys", UserPrefs[].class);

//or u can do async
new EasySave(context)
                .retrieveListAsync("my keys", UserPrefs[].class, new LoadAsyncCallback<List<UserPrefs>>() {
                    @Override
                    public void onComplete(final List<UserPrefs> dataList) {
		    
                        doWhatever(dataList);
                    
		    }

                    @Override
                    public void onError(final String message) {
                        showMessage(message);
                    }
                });
```

###### Warning

If the object or list does not exists, the EasySave will return `null`, so do the assertions.


## Installing

Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
and about dependences:
```
dependencies {
	        compile 'com.github.Guilherme-HRamos:Easy-Save:{$version}'
	}
```

## How this works

All objects and lists are saved in cache. If you want look the source code.

##### Dependencies

Gson by Google (https://github.com/google/gson)

## Contribution

Pull requests are welcome!
Feel free to contribute to Easy-Save.

If you've fixed a bug or have a feature you've added, just create a pull request.

If you've found a bug, want a new feature, or have other questions, file an issue. We will try to answer as soon as possible.

##### If you will use this library, tell me! I'll be very happy to know and put the app name here.

## License

```
MIT License

Copyright (c) 2018 Guilherme Ramos

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
