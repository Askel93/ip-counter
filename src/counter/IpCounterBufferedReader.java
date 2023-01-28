package counter;

import parser.IpParser;
import parser.IpParserImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

/**
 * The class should not be used (use IpCounterBufferedInputStream instead)
 * Left for performance comparison
 */
@Deprecated()
public class IpCounterBufferedReader implements IpCounter {
    private int count = 0;
    private IpParser ipParser = new IpParserImpl();

    @Override
    public int getCountOfDistinctIp(String fileName) {
        int[] ipParts = new int[4];
        boolean [][][][] ipStorage = new boolean[256][][][];

        Path path = Path.of(fileName);
        File file = path.toFile();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = bufferedReader.readLine())!= null){
                ipParser.parseStringToIpPartsArray(line, ipParts);
                checkIpInStorage(ipStorage, ipParts);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        path = null;

        return count;
    }

    private void checkIpInStorage(boolean [][][][] ipStorage, int[] ipParts){

        if(ipStorage[ipParts[0]] == null){
            ipStorage[ipParts[0]] = new boolean[256][][];
        }
        if(ipStorage[ipParts[0]][ipParts[1]] == null){
            ipStorage [ipParts[0]][ipParts[1]] = new boolean[256][];
        }
        if(ipStorage[ipParts[0]][ipParts[1]][ipParts[2]] == null){
            ipStorage[ipParts[0]][ipParts[1]][ipParts[2]] = new boolean[256];
        }
        if(!ipStorage[ipParts[0]][ipParts[1]][ipParts[2]][ipParts[3]]){
            ipStorage[ipParts[0]][ipParts[1]][ipParts[2]][ipParts[3]] = true;
            count++;
        }

    }
}
