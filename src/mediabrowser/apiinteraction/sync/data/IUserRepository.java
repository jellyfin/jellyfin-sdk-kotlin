package mediabrowser.apiinteraction.sync.data;

import mediabrowser.model.dto.UserDto;
import java.util.ArrayList;

public interface IUserRepository {

    void addOrUpdate(String id, UserDto user);

    void delete(String id);

    UserDto get(String id);

    ArrayList<UserDto> getAll();
}
