package org.ikropachev.bdater;

import org.ikropachev.bdater.model.Role;
import org.ikropachev.bdater.model.User;
import org.ikropachev.bdater.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.ikropachev.bdater.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "password");
    public static final int ADMIN_ID = START_SEQ + 2;
    public static final int USER_ID = START_SEQ + 3;

    public static final User admin =
            new User(ADMIN_ID, "admin", "admin@test.test", "admin", Role.ADMIN);
    public static final User user =
            new User(USER_ID, "user", "user@test.test", "user", Role.USER);

    //Users must be sorted by name, and e-mail for name duplicates.
    public static final List<User> users = List.of(admin, user);

    public static User getNew() {
        return new User(null, "New_User", "new@test.test", "newPass", false, new Date(),
                Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setEmail("updated@test.test");
        updated.setName("Updated_Name");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
