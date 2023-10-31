package markup;

import java.util.List;

public class Emphasis extends AbstractHighlight {
    public Emphasis(List<HighlightedText> objects) {
        super(objects);
    }

    @Override
    protected void mark(StringBuilder builder) {
        builder.append("*");
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        builder.append("[i]");
        super.toBBCode(builder);
        builder.append("[/i]");
    }
}
