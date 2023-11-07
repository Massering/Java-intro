package markup;

import java.util.List;

public class Paragraph implements Markdown, BBCode {
    List<HighlightedText> objects;
    public Paragraph(List<HighlightedText> objects) {
        this.objects = objects;
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        for (var obj : objects) {
            obj.toMarkdown(builder);
        }
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        for (var obj : objects) {
            obj.toBBCode(builder);
        }
    }
}
