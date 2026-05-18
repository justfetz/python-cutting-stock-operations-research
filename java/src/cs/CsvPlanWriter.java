package cs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvPlanWriter {
    public void write(Path outputPath, List<RollPlan> rolls) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("roll_id,piece_id,order_id,piece_width,used_width,waste");
        for (RollPlan roll : rolls) {
            for (Piece piece : roll.getPieces()) {
                lines.add(String.format("%s,%s,%s,%d,%d,%d",
                    roll.getRollId(), piece.getPieceId(), piece.getOrderId(), piece.getWidth(), roll.getUsedWidth(), roll.getWaste()));
            }
        }
        Files.write(outputPath, lines);
    }
}
