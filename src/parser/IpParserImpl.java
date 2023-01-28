package parser;

import java.util.Arrays;

@Deprecated
public class IpParserImpl implements IpParser {

    public static final char POINT = '.';

    @Override
    public void parseStringToIpPartsArray(String ipString, int[] ipParts) {

        setNegativeNumberInArray(ipParts);

        int counter = 0;
        boolean firstCharInNumber = true;
        char symbol;

        for (int i = 0; i < ipString.length(); i++) {
            symbol = ipString.charAt(i);
            if(symbol == POINT){
                counter++;
                firstCharInNumber = true;
            }
            else {
                if(firstCharInNumber){
                    ipParts[counter] = Character.getNumericValue(symbol);
                    firstCharInNumber = false;
                }
                else {
                    ipParts[counter] = ipParts[counter] * 10 + Character.getNumericValue(symbol);
                }
            }
        }

        if(!ipIsValid(ipParts)){
            throw new RuntimeException("Illegal ip adress: " + ipString);
        }
    }

    private void setNegativeNumberInArray(int[] ipParts){
        Arrays.fill(ipParts, -1);
    }

    private boolean ipIsValid(int[] ipParts){
        for (int i = 0; i < ipParts.length; i++) {
            if(ipParts[i] < 0 || ipParts[i]>255){
                return false;
            }
        }
        return true;
    }

}
