package com.kevinearls;

import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PosixPermissions {
    private static final Integer CHMOD_WRITE_MASK = 02;
    private static final Integer CHMOD_READ_MASK = 04;
    private static final Integer CHMOD_EXECUTE_MASK = 01;

    private boolean validatePermissions(String permissionsString) {
        permissionsString = permissionsString.trim();
        if (permissionsString.length() < 3 || permissionsString.length() > 4) {
            return false;
        }
        // ignore leading digit for now
        permissionsString = permissionsString.substring(permissionsString.length() - 3);  // if 4 digits chop off leading one
        for (int i=0; i < permissionsString.length(); i++) {
            Character c = permissionsString.charAt(i);
            if (!Character.isDigit(c)) {
                System.out.println("Illegal character " + c);
                return false;
            }
            Integer value = Integer.parseInt(c.toString());
            if (value > 7) {
                System.out.println("Cannot use value > 7 " + value);
                return false;
            }
        }
        return true;
    }

    private Set<PosixFilePermission> getPermissions(String chmodString) {
        Set<PosixFilePermission> permissions = new HashSet<>();
        if (!validatePermissions(chmodString)) {
            return permissions;
        }

        chmodString = chmodString.substring(chmodString.length() - 3);  // if 4 digits chop off leading one

        Integer ownerValue = Integer.parseInt(chmodString.substring(0, 1));
        Integer groupValue = Integer.parseInt(chmodString.substring(1, 2));
        Integer othersValue = Integer.parseInt(chmodString.substring(2, 3));

        if ((ownerValue & CHMOD_WRITE_MASK) > 0) { permissions.add(PosixFilePermission.OWNER_WRITE);}
        if ((ownerValue & CHMOD_READ_MASK) > 0) { permissions.add(PosixFilePermission.OWNER_READ);}
        if ((ownerValue & CHMOD_EXECUTE_MASK) > 0) { permissions.add(PosixFilePermission.OWNER_EXECUTE);}

        if ((groupValue & CHMOD_WRITE_MASK) > 0) { permissions.add(PosixFilePermission.GROUP_WRITE);}
        if ((groupValue & CHMOD_READ_MASK) > 0) { permissions.add(PosixFilePermission.GROUP_READ);}
        if ((groupValue & CHMOD_EXECUTE_MASK) > 0) { permissions.add(PosixFilePermission.GROUP_EXECUTE);}

        if ((othersValue & CHMOD_WRITE_MASK) > 0) { permissions.add(PosixFilePermission.OTHERS_WRITE);}
        if ((othersValue & CHMOD_READ_MASK) > 0) { permissions.add(PosixFilePermission.OTHERS_READ);}
        if ((othersValue & CHMOD_EXECUTE_MASK) > 0) { permissions.add(PosixFilePermission.OTHERS_EXECUTE);}

        return permissions;
    }


    public static void main(String[] args) {
        for (int i = 0; i <= 7; i++) {
            System.out.println("i: " + i + " Read " + (i & CHMOD_READ_MASK) + " Write  " + (i & CHMOD_WRITE_MASK) + " Execute " + (i & CHMOD_EXECUTE_MASK));
        }
        PosixPermissions pp = new PosixPermissions();
        String[] testValuesArray = {
            "000", "abx", "0431", "644", "01234", "0755", "777"
        };
        List<String> testValues = Arrays.asList(testValuesArray);
        for (String testValue : testValues) {
            Set<PosixFilePermission> permissions = pp.getPermissions(testValue);
            System.out.println("Value " + testValue + " result " + PosixFilePermissions.toString(permissions));
        }
    }

}
