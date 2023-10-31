package markup;

import java.util.List;

public abstract class AbstractHighlight implements HighlightedText {
    List<HighlightedText> objects;

    AbstractHighlight(List<HighlightedText> objects) {
        this.objects = objects;
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        mark(builder);
        for (var obj : objects) {
            obj.toMarkdown(builder);
        }
        mark(builder);
    }

    protected abstract void mark(StringBuilder builder);

    @Override
    public void toBBCode(StringBuilder builder) {
        for (var obj : objects) {
            obj.toBBCode(builder);
        }
    }
}
