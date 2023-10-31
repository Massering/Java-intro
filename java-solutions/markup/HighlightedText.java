package markup;

public interface HighlightedText extends Markdown, BBCode {
    void toBBCode(StringBuilder builder);

    void toMarkdown(StringBuilder builder);
}
