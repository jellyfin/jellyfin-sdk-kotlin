package mediabrowser.apiinteraction;

import java.util.Observable;

public class AutomaticObservable extends Observable {

    @Override
    public void notifyObservers(Object data){
        setChanged();
        super.notifyObservers(data);
    }

}
