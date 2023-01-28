package counter;

import parser.IpParser;
import parser.IpParserImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The class should not be used (use IpCounterBufferedInputStream instead)
 * Left for performance comparison
 */
@Deprecated
public class IpCounterFilesLines implements IpCounter {
    private int count = 0;
    private IpParser ipParser = new IpParserImpl();

    @Override
    public int getCountOfDistinctIp(String fileName) {
        int[] ipParts = new int[4];
        boolean [][][][] ipStorage = new boolean[256][][][];

        var path = Path.of(fileName);

        try (var lines = Files.lines(path)) {
            lines.forEach(line -> {
                ipParser.parseStringToIpPartsArray(line, ipParts);
                checkIpInStorage(ipStorage, ipParts);
            });

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
