package markup;

public class ListItem implements BBCode {
    java.util.List<AbstractList> objects;

    public ListItem(java.util.List<AbstractList> objects) {
        this.objects = objects;
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        builder.append("[*]");
        for (AbstractList obj : objects) {
            obj.toBBCode(builder);
        }
    }
}
