package cs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvOrdersReader {
    public List<OrderLine> read(Path inputPath) throws IOException {
        List<String> lines = Files.readAllLines(inputPath);
        List<OrderLine> orders = new ArrayList<>();
        for (int index = 1; index < lines.size(); index++) {
            String line = lines.get(index).trim();
            if (line.isEmpty()) continue;
            String[] tokens = line.split(",");
            orders.add(new OrderLine(
                Integer.parseInt(tokens[0].trim()),
                tokens[1].trim(),
                Integer.parseInt(tokens[2].trim()),
                Integer.parseInt(tokens[3].trim())
            ));
        }
        return orders;
    }
}
