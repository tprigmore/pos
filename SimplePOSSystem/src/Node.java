
public class Node<T> implements Comparable<T>
{

	private Node<T> left;
	private Node<T> right;
	private Node<T> prev;
	private T el;

	public Node()
	{
		left = null;
		right = null;
		prev = null;
		el = null;

	}

	public Node(T el)
	{
		this.left = null;
		this.right = null;
		this.prev = null;
		this.el = el;
	}

	public void setRight(Node<T> p)
	{
		this.right = p;
	}

	public void setLeft(Node<T> p)
	{
		this.left = p;
	}

	public void setNext(Node<T> p)
	{
		this.right = p;
	}

	public void setPrev(Node<T> p)
	{
		this.prev = p;
	}

	public void setElement(T el)
	{
		this.el = el;
	}

	public Node<T> getRight()
	{
		return this.right;
	}

	public Node<T> getLeft()
	{
		return this.left;
	}

	public Node<T> getNext()
	{
		return this.right;
	}

	public Node<T> getPrev()
	{
		return this.prev;
	}

	public T getElement()
	{
		return this.el;
	}

	public boolean equals(Object obj)
	{
		return this.el.equals(obj);
	}

	public String toString()
	{
		return this.el.toString() + "\n";
	}

	public int compareTo(Object obj)
	{
		return ((Comparable<T>) this.el).compareTo((T) obj);
	}
}