package markup;

import java.util.List;

public class Strikeout extends AbstractHighlight {
    public Strikeout(List<HighlightedText> objects) {
        super(objects);
    }

    @Override
    protected void mark(StringBuilder builder) {
        builder.append("~");
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        builder.append("[s]");
        super.toBBCode(builder);
        builder.append("[/s]");
    }
}
