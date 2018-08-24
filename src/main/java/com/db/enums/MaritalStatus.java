package com.db.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MaritalStatus {
    SINGLE(1, "холост"),
    MARRIED(2, "жената"),
    DIVORCED(3, "разведена");

    private final int dbCode;
    private final String russDesc;
//    private static final HashMap<Integer, MaritalStatus> instances = new HashMap<>();

    static {
        /*instances.put(1, SINGLE);
        instances.put(2, MARRIED);
        instances.put(3, DIVORCED);*/
    }

    public static MaritalStatus findByDbCode(int dbCode){
        MaritalStatus[] statuses = values();
        for (MaritalStatus status : statuses) {
            if (status.dbCode == dbCode){
                return status;
            }
        }
        return null;
    }
}
