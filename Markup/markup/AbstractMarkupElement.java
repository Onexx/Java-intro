package markup;

import java.util.List;

public abstract class AbstractMarkupElement {
    private final List<CommonMarkupElement> elements;

    protected AbstractMarkupElement(List<CommonMarkupElement> elements){
        this.elements = elements;
    }

    protected void toMarkdown(StringBuilder result, String border){
        result.append(border);
        for(CommonMarkupElement element: elements){
            element.toMarkdown(result);
        }
        result.append(border);
    }

    protected void toBBCode(StringBuilder result, String borderLeft, String borderRight){
        result.append(borderLeft);
        for(CommonMarkupElement element: elements){
            element.toBBCode(result);
        }
        result.append(borderRight);
    }
}
