package markup;

import java.util.List;

public class Strong extends AbstractHighlight {
    public Strong(List<HighlightedText> objects) {
        super(objects);
    }

    @Override
    protected void mark(StringBuilder builder) {
        builder.append("__");
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        builder.append("[b]");
        super.toBBCode(builder);
        builder.append("[/b]");
    }
}
