package com.codermason.gatewaystaff.staff;

/**
 * created by codermason on 2/16/14
 */
public class Staff {

    private String name, rank;

    public Staff(String name, String rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return this.name;
    }

    public String getRank() {
        return this.rank;
    }

}
