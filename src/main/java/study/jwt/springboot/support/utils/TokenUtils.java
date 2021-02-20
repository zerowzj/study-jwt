package study.jwt.springboot.support.utils;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TokenUtils {

    public static String createToken() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        EthernetAddress ethernetAddress = EthernetAddress.fromInterface();
        TimeBasedGenerator generator = Generators.timeBasedGenerator();
        String token = generator.generate().toString();
        log.info(">>>>>> create token cost time [{}] ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return token;
    }

    public static String createTokenV2() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String token = UUID.randomUUID().toString();
        log.info(">>>>>> create token cost time [{}] ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return token;
    }
}
