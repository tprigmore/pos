
public class Node<T>
{

	private Node<T> next;
	private Node<T> prev;
	private T el;

	public Node()
	{
		next = null;
		prev = null;
		el = null;

	}

	public Node(T el)
	{
		this.next = null;
		this.prev = null;
		this.el = el;
	}

	public void setNext(Node<T> p)
	{
		this.next = p;
	}

	public void setPrev(Node<T> p)
	{
		this.prev = p;
	}

	public void setElement(T el)
	{
		this.el = el;
	}

	public Node<T> getNext()
	{
		return this.next;
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
}