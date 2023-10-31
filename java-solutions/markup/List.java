package markup;

public abstract class List extends AbstractList {
    java.util.List<ListItem> objects;

    public List(java.util.List<ListItem> objects) {
        this.objects = objects;
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        for (ListItem obj : objects) {
            obj.toBBCode(builder);
        }
    }
}
