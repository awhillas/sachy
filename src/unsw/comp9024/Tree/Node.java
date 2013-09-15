/**
 * 
 */
package unsw.comp9024.Tree;

/**
 * @author alex
 *
 */
public interface Node {
	/**
	 * Is the string contained in this Node or one of tits children?
	 * @param needle	
	 * @return	true id found, false otherwise.
	 */
	public boolean contains (String needle);

	public void insert(String s);

	public int depth();

	public void display(int depth);
}
