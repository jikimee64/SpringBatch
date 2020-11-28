package com.Colllections_Framework.Hashmap;

public class Main {
    public static void main(String[] args) {

        MemberHashMap manager = new MemberHashMap();

        Member memberLee = new Member(100, "Lee");
        Member memberKim = new Member(200, "Kim");
        Member memberPark = new Member(300, "Park");

        manager.addMember(memberLee);
        manager.addMember(memberKim);
        manager.addMember(memberPark);

        manager.showAllMember();
    }
}