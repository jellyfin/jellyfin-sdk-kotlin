package mediabrowser.apiinteraction.android.sync.data;

import mediabrowser.model.users.UserAction;
import java.util.ArrayList;

public interface IUserActionRepository {

    void createUserAction(UserAction action);

    void deleteUserAction(UserAction action);

    ArrayList<UserAction> getUserActions(String serverId);

}
