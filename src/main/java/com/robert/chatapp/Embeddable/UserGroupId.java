package com.robert.chatapp.Embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserGroupId implements Serializable {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "group_id")
    private int groupId;

    public UserGroupId() {
    }

    public UserGroupId(int userId, int groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserGroupId that = (UserGroupId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(groupId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, groupId);
    }
}
