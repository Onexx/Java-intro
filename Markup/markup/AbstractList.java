package markup;

import java.util.List;

public abstract class AbstractList implements ListInterface{
    private final List<ListItem> elements;

    protected AbstractList(List<ListItem> elements) {
        this.elements = elements;
    }

    protected void toBBCode(StringBuilder result, String borderLeft, String borderRight) {
        result.append(borderLeft);
        for (ListItem element : elements) {
            element.toBBCode(result);
        }
        result.append(borderRight);
    }
}
