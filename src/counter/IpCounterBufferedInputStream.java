package counter;

import core.Ip;
import core.IpStorage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class IpCounterBufferedInputStream implements IpCounter {
    private static final byte POINT = 46;
    private static final byte CARRIAGE_RETURN = 13;
    private static final byte NEW_LINE = 10;
    private static final int NUMERIC_OFFSET = 48;

    private final IpStorage ipStorage = new IpStorage();
    private final Ip ip = new Ip();

    @Override
    public int getCountOfDistinctIp(String fileName) {

        var ipParts = ip.getIpParts();
        ip.setNegativeNumbersToParts();

        Path path = Path.of(fileName);
        File file = path.toFile();

        try (var in = new BufferedInputStream(new FileInputStream(file))) {

            int ipPartCounter = 0;
            boolean firstCharInNumber = true;
            
            var buffer = new byte[getBufferSize()];
            int lengthRead;

            while ((lengthRead = in.read(buffer))>0){
                for (int  i = 0;  i < lengthRead;  i++) {
                    switch (buffer[i]) {
                        case POINT -> {
                            ipPartCounter++;
                            firstCharInNumber = true;
                        }
                        case CARRIAGE_RETURN, NEW_LINE -> {
                            if (buffer[i] == NEW_LINE) {
                                ipPartCounter = 0;
                                firstCharInNumber = true;
                                updateIpStorage();
                            }
                        }
                        default -> {
                            if (firstCharInNumber) {
                                firstCharInNumber = false;
                                ipParts[ipPartCounter] = buffer[i] - NUMERIC_OFFSET;
                            } else {
                                ipParts[ipPartCounter] = ipParts[ipPartCounter] * 10 + (buffer[i] - NUMERIC_OFFSET);
                            }
                        }
                    }
                }
            }
            updateIpStorage();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        path = null;

        return ipStorage.getCount();
    }

    private int getBufferSize(){
        //might be some logic based on file size
        return 4096;
    }

    private void updateIpStorage() {
        if (!ip.isValid()) {
            throw new RuntimeException("Illegal ip adress: " + ip);
        }
        ipStorage.changeCountBasedOnIpPart(ip);
        ip.setNegativeNumbersToParts();
    }

}
