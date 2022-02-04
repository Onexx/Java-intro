package markup;

public interface CommonMarkupElement {
    void toBBCode(StringBuilder result);
    void toMarkdown(StringBuilder result);
}
