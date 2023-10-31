package markup;

public class UnorderedList extends List {
    public UnorderedList(java.util.List<ListItem> objects) {
        super(objects);
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        builder.append("[list]");
        super.toBBCode(builder);
        builder.append("[/list]");
    }
}
