package core;

public class IpStorage {

    private int count = 0;
    private final boolean [][][][] ipStorageArray = new boolean[256][][][];

    public int getCount() {
        return count;
    }

    public boolean[][][][] getIpStorageArray() {
        return ipStorageArray;
    }

    public void changeCountBasedOnIpPart(Ip ip){

        var ipParts = ip.getIpParts();

        if(ipStorageArray[ipParts[0]] == null){
            ipStorageArray[ipParts[0]] = new boolean[256][][];
        }
        if(ipStorageArray[ipParts[0]][ipParts[1]] == null){
            ipStorageArray[ipParts[0]][ipParts[1]] = new boolean[256][];
        }
        if(ipStorageArray[ipParts[0]][ipParts[1]][ipParts[2]] == null){
            ipStorageArray[ipParts[0]][ipParts[1]][ipParts[2]] = new boolean[256];
        }
        if(!ipStorageArray[ipParts[0]][ipParts[1]][ipParts[2]][ipParts[3]]){
            ipStorageArray[ipParts[0]][ipParts[1]][ipParts[2]][ipParts[3]] = true;
            count++;
        }

    }
}
