package core;

import java.util.Arrays;

public class Ip {
    private final int[] ipParts = new int[4];

    public int[] getIpParts() {
        return ipParts;
    }

    public boolean isValid(){
        for (int ipPart : ipParts) {
            if (ipPart < 0 || ipPart > 255) {
                return false;
            }
        }
        return true;
    }

    public void setNegativeNumbersToParts(){
        Arrays.fill(ipParts, Integer.MIN_VALUE);
    }
}
