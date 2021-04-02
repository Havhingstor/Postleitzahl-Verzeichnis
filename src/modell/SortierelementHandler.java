package modell;

public interface SortierelementHandler<S extends Sortierelement<I>,I> {
	public void handle(S s);
}
