import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class task3 {
    public static void updateTests(JsonNode testsNode, JsonNode valuesNode) {
        Iterator<JsonNode> valuesIterator = valuesNode.get("values").elements();
        testsNode = testsNode.get("tests");

        while (valuesIterator.hasNext()) {
            JsonNode valueNode = valuesIterator.next();
            updateTestById(testsNode, valueNode);
        }
    }

    private static void updateTestById(JsonNode testsNode, JsonNode valueNode) {
        int idToUpdate = valueNode.get("id").asInt();
        String newValue = valueNode.get("value").asText();

        for (JsonNode test : testsNode) {
            if (test.get("id").asInt() == idToUpdate) {
                ((ObjectNode) test).put("value", newValue);
            }

            if (test.has("values")) {
                updateTestById(test.get("values"), valueNode);
            }
        }
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode valuesNode = objectMapper.readTree(new File(args[0]));
            JsonNode testsNode = objectMapper.readTree(new File(args[1]));

            updateTests(testsNode, valuesNode);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(args[2]), testsNode);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
