package com.company;

import lt.infobalt.itakademija.javalang.exam.socialnetwork.Friend;
import lt.infobalt.itakademija.javalang.exam.socialnetwork.FriendNotFoundException;
import lt.infobalt.itakademija.javalang.exam.socialnetwork.SocialNetwork;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

public class MySocialNetwork implements SocialNetwork {

    private Collection<Friend> friends;

    public MySocialNetwork() {
        this.friends = new HashSet<>();
    }

    @Override
    public void addFriend(Friend friend) {
        if (friend != null) {
            friends.add(friend);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getNumberOfFriends() {
        return friends.size();
    }

    @Override
    public Friend findFriend(String s, String s1) throws FriendNotFoundException {
        if (s == null || s1 == null) {
            throw new IllegalArgumentException();
        }
        return friends.stream()
                .filter(entry -> entry.getFirstName().equals(s) && entry.getLastName().equals(s1))
                .findFirst().orElseThrow(() -> new FriendNotFoundException(s, s1));
    }

    @Override
    public Collection<Friend> findByCity(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        } else {
            return friends.stream()
                    .filter(entry -> entry.getCity().equals(s))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Collection<Friend> getOrderedFriends() {
        return friends.stream()
                .sorted(Comparator.comparing(Friend::getCity).thenComparing(Friend::getLastName).thenComparing(Friend::getFirstName))
                .collect(Collectors.toList());
    }
}
