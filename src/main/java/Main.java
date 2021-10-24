import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws URISyntaxException, IOException {

        objectMapper.registerModule(new JavaTimeModule());

        // Load JSON file
        URL resource = Main.class.getClassLoader().getResource("example.json");
        byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
        String json = new String(bytes);

        JsonNode node = objectMapper.readTree(json);

        // System.out.println(node);

        if (node.isArray()) {
            for (JsonNode jsonNode : node) {
                TvSeries t = objectMapper.treeToValue(jsonNode, TvSeries.class);
                System.out.println(t.getId());
                System.out.println(t.getTitle());
                System.out.println(t.getCategory());
                System.out.println(t.getReleaseDate());

                for (Actor actor : t.getActors()) {
                    System.out.println(actor.getName());
                }
            }

        }

    }

}
