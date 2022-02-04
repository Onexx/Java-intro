package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
    public UnorderedList(List<ListItem> elements) {
        super(elements);
    }

    @Override
    public void toBBCode(StringBuilder result) {
        super.toBBCode(result, "[list]", "[/list]");
    }

    @Override
    public void toMarkdown(StringBuilder result) {

    }
}
