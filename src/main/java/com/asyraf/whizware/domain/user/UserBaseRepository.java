package com.asyraf.whizware.domain.user;

import com.asyraf.whizware.domain.BaseRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserBaseRepository extends BaseRepository<User, UUID> {
    public UserBaseRepository() {
        super(User.class);
    }

    public Optional<User> getByUsername(String username) {
        List<User> user = query("FROM User u WHERE username = :username", Map.of("username", username));
        return user.isEmpty() ? Optional.empty() : Optional.of(user.get(0));

    }
}
