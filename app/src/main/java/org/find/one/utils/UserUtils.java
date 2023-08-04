package org.find.one.utils;

import org.find.one.R;
import org.find.one.data.model.User;

import java.util.ArrayList;
import java.util.Random;

public class UserUtils {
    private final static ArrayList<User> userList = new ArrayList<>();

    private final static ArrayList<User> friendUserList = new ArrayList<>();

    private final static Random random = new Random();

    private static int myImgRes = R.drawable.cat;

    public static void initUserList() {
        userList.add(new User(1L, "Tom", "M", 21, "happy", R.drawable.cat));
        userList.add(new User(2L, "Shine", "F", 24, "happy", R.drawable.dog));
        userList.add(new User(3L, "Joy", "M", 31, "happy", R.drawable.cat));
        userList.add(new User(4L, "Ben", "M", 19, "happy", R.drawable.dog));
        userList.add(new User(5L, "Ming", "F", 22, "happy", R.drawable.cat));
        userList.add(new User(6L,"Jerry", "M", 24, "happy", R.drawable.dog));
        userList.add(new User(7L, "Lily", "F", 27, "happy", R.drawable.cat));
    }

    public static User getRandomUser() {
        if (userList.size() <= 100) {
            userList.add(new User("User" + random.nextInt(), "M", random.nextInt(100), "happy", random.nextInt(2) % 2 == 0 ? R.drawable.cat : R.drawable.dog));
        }
        return userList.get(random.nextInt(userList.size()));
    }

    public static void addFriend(User user) {
        friendUserList.add(user);
        userList.remove(user);
    }

    public static ArrayList<User> getFriendUserList() {
        return friendUserList;
    }

    public static User getUserById(long id) {
        for (User user : friendUserList) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    public static int getMyImgRes() {
        return myImgRes;
    }

    public static void setMyImgRes(int myImgRes) {
        UserUtils.myImgRes = myImgRes;
    }
}
