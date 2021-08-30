import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class App {
    public static void main(String[] args) throws IOException {
        Instant instant = Instant.now();
        String upload = instant.toString().substring(0,23)+"Z";
        Instant yesterday = instant.minus(1, ChronoUnit.DAYS);
        final int STEPS = 2000;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("multiple.txt"))){
            bw.write("[");
            bw.write("\n  {\n" +
                    "    \"endTime\": \""+instant.toString().substring(0,23)+"Z\",\n" +
                    "    \"startTime\": \""+yesterday.toString().substring(0,23)+"Z\",\n" +
                    "    \"stepCount\": "+STEPS+"\n" +
                    "    \"uploadedTime\": \""+upload+"\"\n" +
                    "  }");
            instant = yesterday;
            yesterday = instant.minus(1, ChronoUnit.DAYS);
            for(int i = 1; i<1000; i++){
                bw.write(",\n  {\n" +
                        "    \"endTime\": \""+instant.toString().substring(0,23)+"Z\",\n" +
                        "    \"startTime\": \""+yesterday.toString().substring(0,23)+"Z\",\n" +
                        "    \"stepCount\": "+(STEPS-i)+",\n" +
                        "    \"uploadedTime\": \""+upload+"\"\n" +
                        "  }");
                instant = yesterday;
                yesterday = instant.minus(1, ChronoUnit.DAYS);
            }
            bw.write("\n]");
        }
        System.out.println(instant.toString().substring(0,23));
    }
}
