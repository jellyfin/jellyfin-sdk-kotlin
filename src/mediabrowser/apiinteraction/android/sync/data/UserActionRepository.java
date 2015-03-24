package mediabrowser.apiinteraction.android.sync.data;

import mediabrowser.apiinteraction.sync.data.IUserActionRepository;
import mediabrowser.apiinteraction.websocket.ObjectWebSocketMessage;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.users.UserAction;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Luke on 3/24/2015.
 */
public class UserActionRepository implements IUserActionRepository {

    @Override
    public void create(UserAction action) {


    }

    @Override
    public void delete(UserAction action) {


    }

    @Override
    public ArrayList<UserAction> get(String serverId) {
        return new ArrayList<>();
    }
}
