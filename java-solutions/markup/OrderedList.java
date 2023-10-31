package markup;

public class OrderedList extends List {
    public OrderedList(java.util.List<ListItem> objects) {
        super(objects);
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        builder.append("[list=1]");
        super.toBBCode(builder);
        builder.append("[/list]");
    }
}
