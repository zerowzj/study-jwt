package study.jwt.springboot.support.jwt;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

public class TokenGenerator {

    public static String createToken() {
        EthernetAddress ethernetAddress = EthernetAddress.fromInterface();
        TimeBasedGenerator generator = Generators.timeBasedGenerator(ethernetAddress);
        return generator.generate().toString();
    }
}
