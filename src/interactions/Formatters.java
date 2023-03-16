package interactions;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class Formatters {
    public MaskFormatter getFormatterForType(String type) {
        try {
            switch (type.trim().toLowerCase().replace(":", "")) {
                case "phone":
                    var mask = new MaskFormatter("(+63)-###-###-####");
                    mask.setOverwriteMode(true);
                    mask.setValueContainsLiteralCharacters(false);
                    return mask;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
