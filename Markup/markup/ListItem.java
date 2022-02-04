package markup;

import java.util.List;

public class ListItem extends AbstractMarkupElement{
    public ListItem(List<CommonMarkupElement> elements) {
        super(elements);
    }

    public void toBBCode(StringBuilder result) {
        super.toBBCode(result,"[*]","");
    }
}
