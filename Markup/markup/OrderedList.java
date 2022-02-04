package markup;

import java.util.List;

public class OrderedList extends AbstractList {
    public OrderedList(List<ListItem> elements) {
        super(elements);
    }

    @Override
    public void toBBCode(StringBuilder result) {
        super.toBBCode(result, "[list=1]", "[/list]");
    }

    @Override
    public void toMarkdown(StringBuilder result) {

    }
}
