package markup;

import java.util.List;

public class Strikeout extends AbstractMarkupElement implements ParagraphInterface {
    public Strikeout(List<CommonMarkupElement> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "~");
    }

    @Override
    public void toBBCode(StringBuilder result) {
        toBBCode(result, "[s]", "[/s]");
    }
}
