package backend.academy.samples.statisticTests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

public class BaseStatisticTest {

    protected static String baseDir = System.getProperty("user.dir") + "/examples";
    protected static String firstFileName = "logs_1.txt";
    protected static String secondFileName = "log_2.txt";

    @BeforeAll
    public static void setupFiles() throws IOException {
        File directory = new File(baseDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        createFile(firstFileName,
"""
93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
93.180.71.3 - - [17/May/2015:08:05:23 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
80.91.33.133 - - [17/May/2015:08:05:24 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"
217.168.17.5 - - [17/May/2015:08:05:34 +0000] "GET /downloads/product_1 HTTP/1.1" 200 490 "-" "Debian APT-HTTP/1.3 (0.8.10.3)"
217.168.17.5 - - [17/May/2015:08:05:09 +0000] "GET /downloads/product_2 HTTP/1.1" 200 490 "-" "Debian APT-HTTP/1.3 (0.8.10.3)"
93.180.71.3 - - [17/May/2015:08:05:57 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
217.168.17.5 - - [17/May/2015:08:05:02 +0000] "GET /downloads/product_2 HTTP/1.1" 404 337 "-" "Debian APT-HTTP/1.3 (0.8.10.3)"
217.168.17.5 - - [17/May/2015:08:05:42 +0000] "GET /downloads/product_1 HTTP/1.1" 404 332 "-" "Debian APT-HTTP/1.3 (0.8.10.3)"
80.91.33.133 - - [17/May/2015:08:05:01 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)"
93.180.71.3 - - [17/May/2015:08:05:27 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
217.168.17.5 - - [17/May/2015:08:05:12 +0000] "GET /downloads/product_2 HTTP/1.1" 200 3316 "-" "-"
188.138.60.101 - - [17/May/2015:08:05:49 +0000] "GET /downloads/product_2 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.9.7.9)"
80.91.33.133 - - [17/May/2015:08:05:14 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)"
37.187.252.103 - - [21/May/2015:06:05:20 +0000] "GET /downloads/product_2 HTTP/1.1" 404 340 "-" "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
37.187.252.103 - - [21/May/2015:06:05:42 +0000] "GET /downloads/product_2 HTTP/1.1" 404 337 "-" "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
37.187.252.103 - - [21/May/2015:06:05:15 +0000] "GET /downloads/product_2 HTTP/1.1" 404 339 "-" "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
37.187.252.103 - - [21/May/2015:06:05:30 +0000] "GET /downloads/product_2 HTTP/1.1" 404 336 "-" "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
37.187.252.103 - - [21/May/2015:06:05:21 +0000] "GET /downloads/product_2 HTTP/1.1" 404 341 "-" "Debian APT-HTTP/1.3 (1.0.1ubuntu2)"
"""
        );
        createFile(secondFileName,
"""
65.23.86.144 - - [27/Oct/2024:16:08:06 +0000] "GET /Ameliorated_complexity_Synergistic.css HTTP/1.1" 200 2098 "-" "Opera/10.44 (X11; Linux x86_64; en-US) Presto/2.12.213 Version/12.00"
164.229.148.37 - - [27/Oct/2024:16:08:06 +0000] "GET /disintermediate_Polarised-hybrid.php HTTP/1.1" 200 1488 "-" "Mozilla/5.0 (X11; Linux i686) AppleWebKit/5332 (KHTML, like Gecko) Chrome/38.0.800.0 Mobile Safari/5332"
46.255.193.81 - - [27/Oct/2024:16:08:06 +0000] "GET /optimal.htm HTTP/1.1" 200 1672 "-" "Mozilla/5.0 (X11; Linux x86_64; rv:7.0) Gecko/1976-07-04 Firefox/36.0"
247.148.15.171 - - [27/Oct/2024:16:08:06 +0000] "GET /project/productivity/Assimilated.js HTTP/1.1" 200 849 "-" "Mozilla/5.0 (Macintosh; PPC Mac OS X 10_5_4 rv:4.0) Gecko/2001-07-07 Firefox/37.0"
13.69.144.106 - - [27/Oct/2024:16:08:09 +0000] "GET /Innovative%20stable%20contingency/Re-contextualized.gif HTTP/1.1" 200 2653 "-" "Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_8_8) AppleWebKit/5322 (KHTML, like Gecko) Chrome/37.0.845.0 Mobile Safari/5322"
220.58.33.10 - - [27/Oct/2024:16:08:09 +0000] "POST /incremental/Stand-alone%20architecture_Decentralized.jpg HTTP/1.1" 200 2438 "-" "Mozilla/5.0 (iPhone; CPU iPhone OS 8_1_1 like Mac OS X; en-US) AppleWebKit/534.8.3 (KHTML, like Gecko) Version/4.0.5 Mobile/8B118 Safari/6534.8.3"
12.170.116.3 - - [27/Oct/2024:16:08:09 +0000] "GET /circuit/Total/matrices_strategy.js HTTP/1.1" 200 2375 "-" "Mozilla/5.0 (Macintosh; PPC Mac OS X 10_8_6 rv:7.0; en-US) AppleWebKit/532.10.4 (KHTML, like Gecko) Version/5.0 Safari/532.10.4"
134.35.194.50 - - [27/Oct/2024:16:08:09 +0000] "GET /Object-based.hmtl HTTP/1.1" 200 1126 "-" "Mozilla/5.0 (X11; Linux x86_64; rv:8.0) Gecko/1991-24-09 Firefox/37.0"
242.159.207.144 - - [27/Oct/2024:16:08:10 +0000] "PUT /homogeneous_mission-critical_Decentralized/Organic%20Networked.php HTTP/1.1" 200 1023 "-" "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1_1 like Mac OS X; en-US) AppleWebKit/532.33.4 (KHTML, like Gecko) Version/5.0.5 Mobile/8B116 Safari/6532.33.4"
152.92.118.139 - - [27/Oct/2024:16:08:10 +0000] "GET /Switchable_Digitized-fresh-thinking/pricing%20structure%20Advanced.jpg HTTP/1.1" 200 1697 "-" "Opera/8.60 (X11; Linux i686; en-US) Presto/2.9.192 Version/12.00"
98.89.76.39 - - [27/Oct/2024:16:08:10 +0000] "DELETE /neutral.php HTTP/1.1" 200 1426 "-" "Mozilla/5.0 (X11; Linux i686; rv:7.0) Gecko/1906-23-05 Firefox/35.0"
"""
            );
    }

    private static void createFile(String fileName, String content) throws IOException {
        File file = new File(baseDir + "/" + fileName);
        if (!file.exists()) {
            Files.createFile(Paths.get(file.getPath()));
        }
        Files.write(Paths.get(file.getPath()), content.getBytes());
    }

    @AfterAll
    public static void cleanupFiles() {
        File directory = new File(baseDir);
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                file.delete();
            }
            directory.delete();
        }
    }
}
