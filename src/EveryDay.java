import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class EveryDay {
    public static void main(String[] args) throws IOException {
        Instant instant = Instant.now();
        //String upload = instant.toString().substring(0,23)+"Z";
        Instant yesterday = instant.minus(1, ChronoUnit.DAYS);
        final int STEPS = 2000;
        String endtime = instant.toString().substring(0,23);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("multiple.txt"))){
            bw.write("[");
            bw.write("\n  {\n" +
                    "    \"endTime\": \""+endtime+"Z\",\n" +
                    "    \"startTime\": \""+yesterday.toString().substring(0,23)+"Z\",\n" +
                    "    \"stepCount\": "+STEPS+",\n" +
                    "    \"uploadedTime\": \""+endtime+"Z\"\n" +
                    "  }");
            for(int i = 1; i<1000; i++){
                instant = yesterday;
                yesterday = instant.minus(1, ChronoUnit.DAYS);
                endtime = instant.toString().substring(0,23);
                bw.write(",\n  {\n" +
                        "    \"endTime\": \""+endtime+"Z\",\n" +
                        "    \"startTime\": \""+yesterday.toString().substring(0,23)+"Z\",\n" +
                        "    \"stepCount\": "+(STEPS-i)+",\n" +
                        "    \"uploadedTime\": \""+endtime+"Z\"\n" +
                        "  }");
            }
            bw.write("\n]");
        }
        System.out.println(instant.toString().substring(0,23));
    }
}
