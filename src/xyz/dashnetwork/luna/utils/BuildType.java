package xyz.dashnetwork.luna.utils;

import xyz.dashnetwork.luna.utils.connection.User;

import java.util.function.Predicate;

public enum BuildType {

    ALLOWED(user -> true),
    STAFF(User::isStaff),
    ADMIN(User::isAdmin),
    OWNER(User::isOwner),
    DISALLOWED(user -> false);

    private final Predicate<User> predicate;

    BuildType(Predicate<User> predicate) { this.predicate = predicate; }

    public Predicate<User> getPredicate() { return predicate; }

}
