package com.tolgaozgun.gdscturkweb.enums;

public enum UserType {
    ADMIN(100),
    GOOGLER(100),
    FACILITATOR(50),
    LEAD(10),
    CORE_TEAM_MEMBER(5);

    private int rank;

    UserType(int rank) {
        this.rank = rank;
    }

    public boolean isAbsHigherRankThan(UserType userType) {
        return this.rank > userType.rank;
    }

}
