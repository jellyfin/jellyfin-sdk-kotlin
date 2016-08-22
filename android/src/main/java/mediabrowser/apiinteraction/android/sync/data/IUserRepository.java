package mediabrowser.apiinteraction.android.sync.data;

import mediabrowser.model.dto.UserDto;
import java.util.ArrayList;

public interface IUserRepository {

    void addOrUpdateUser(String id, UserDto user);

    void deleteUser(String id);

    UserDto getUser(String id);

    ArrayList<UserDto> getAllUsers();
}
