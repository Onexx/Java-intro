package markup;

import java.util.List;

public class Paragraph extends AbstractMarkupElement implements ListInterface {
    public Paragraph(List<CommonMarkupElement> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "");
    }

    @Override
    public void toBBCode(StringBuilder result) {
        toBBCode(result, "", "");
    }

}
