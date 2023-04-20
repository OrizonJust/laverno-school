package ru.laverno.entitiy;

import jakarta.annotation.Nonnull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(schema = "school", name = "sc_t_user")
public class User {

    @Id
    @Column(value = "usr_id")
    private final UUID id;

    @Nonnull
    @Column(value = "usr_name")
    private final String username;

    @Nonnull
    @Column(value = "usr_password")
    private final String password;

    @Nonnull
    @Column(value = "usr_email")
    private final String email;

    @Column(value = "usr_phone")
    private final String phone;

    @Column(value = "usr_avatar")
    private final String avatar;

    @Column(value = "usr_disabled")
    private final boolean disabled;

    @MappedCollection(idColumn = "usr_id")
    private final Set<RoleRef> roles;

    public User(String username, String password, String email, String phone, String avatar, boolean disabled, Set<RoleRef> roles) {
        this(null, username, password, email, phone, avatar, disabled, roles);
    }

    @PersistenceCreator
    public User(UUID id, String username, String password, String email, String phone, String avatar, boolean disabled, Set<RoleRef> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.disabled = disabled;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public Set<RoleRef> getRoles() {
        return roles;
    }
}
