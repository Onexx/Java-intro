package markup;

import java.util.List;

public class Strong extends AbstractMarkupElement implements ParagraphInterface {
    public Strong(List<CommonMarkupElement> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "__");
    }

    @Override
    public void toBBCode(StringBuilder result) {
        toBBCode(result, "[b]", "[/b]");
    }
}
