package mediabrowser.apiinteraction.sync.data;

import mediabrowser.model.users.UserAction;
import java.util.ArrayList;

public interface IUserActionRepository {

    void create(UserAction action);

    void delete(UserAction action);

    ArrayList<UserAction> get(String serverId);

}
