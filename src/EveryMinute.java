import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class EveryMinute {
    public static void main(String[] args) throws IOException {
        Instant instant = Instant.now();
        Instant secondBefore = instant.minus(1, ChronoUnit.SECONDS);
        final int STEPS = 2;
        String endTime = instant.toString().substring(0,23);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("seconds.txt"))){
            bw.write("[");
            bw.write("\n  {\n" +
                    "    \"endTime\": \""+ endTime +"Z\",\n" +
                    "    \"startTime\": \""+secondBefore.toString().substring(0,23)+"Z\",\n" +
                    "    \"stepCount\": "+STEPS+"\n" +
                    "    \"uploadedTime\": \""+ endTime +"Z\"\n" +
                    "  }");
            for(int i = 1; i<1000; i++){
                instant = secondBefore;
                secondBefore = instant.minus(1, ChronoUnit.SECONDS);
                endTime = instant.toString().substring(0,23);
                bw.write(",\n  {\n" +
                        "    \"endTime\": \""+ endTime +"Z\",\n" +
                        "    \"startTime\": \""+secondBefore.toString().substring(0,23)+"Z\",\n" +
                        "    \"stepCount\": "+STEPS+",\n" +
                        "    \"uploadedTime\": \""+ endTime +"Z\"\n" +
                        "  }");
            }
            bw.write("\n]");
        }
        System.out.println(instant.toString().substring(0,23));
    }
}
