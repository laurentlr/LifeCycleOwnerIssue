# LifeCycleOwner and ViewModelScope Issue
The [viewModelScope](https://developer.android.com/jetpack/androidx/releases/lifecycle#2.2.0) is using <b>Dispatchers.Main.immediate</b> since [2.2.2-alpha05](https://developer.android.com/jetpack/androidx/releases/lifecycle#2.2.0-alpha05)</br>
In the case of immediate dispatching (main thread) and couple with a <b>LiveDataOwner</b> only the <b>last</b> MutableLiveData is emitted.

<h2>ViewModel</h2>

```
fun initialize() {
        flow {
            //Works with delay(1) or changing dispatcher
            emit("first")
            emit("second")
        }
            .onEach { mutableState.value = it }
            .launchIn(viewModelScope) //Works with Dispatchers.Main

    }
```

<h2>View</h2>

```
myViewModel
           .state
           .observe(this, Observer { //Works when observing for ever
               linear.addView(TextView(this).apply { text = it })
               //only last emitted element is catch
               //Result is: "second"
               //Expected: "first, second"
            })

        myViewModel.initialize()
        
```
