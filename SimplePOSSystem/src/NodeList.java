
public class NodeList<T>
{

	private Node<T> head;
	private Node<T> tail;
	private int count;

	public NodeList()
	{
		head = new Node<T>();
		tail = new Node<T>();
		head.setNext(tail);
		tail.setPrev(head);
		count = 0;
	}

	public NodeList(T el)
	{
		Node<T> temp = new Node<T>(el);
		head = new Node<T>();
		tail = new Node<T>();
		head.setNext(temp);
		tail.setPrev(temp);
		temp.setNext(tail);
		temp.setPrev(head);
		count = 1;
	}

	public void insertAfter(Node<T> p, T el)
	{
		Node<T> temp = new Node<T>(el);
		temp.setNext(p.getNext());
		temp.setPrev(p);
		p.getNext().setPrev(temp);
		p.setNext(temp);
		count++;

	}

	public void insertBefore(Node<T> p, T el)
	{
		Node<T> temp = new Node<T>(el);
		temp.setNext(p);
		temp.setPrev(p.getPrev());
		p.getPrev().setNext(temp);
		p.setPrev(temp);
		count++;
	}

	public void insertFirst(T el)
	{
		insertAfter(head, el);
	}

	public void insertLast(T el)
	{
		insertBefore(tail, el);
	}

	public T remove(Node<T> p)
	{
		T temp = p.getElement();
		p.getPrev().setNext(p.getNext());
		p.getNext().setPrev(p.getPrev());
		p.setPrev(null);
		p.setNext(null);
		p.setElement(null);
		count--;
		return (temp);
	}

	public T removeFirst()
	{
		return (remove(head.getNext()));
	}

	public T removeLast()
	{
		return (remove(tail.getPrev()));
	}

	public T first()
	{
		return head.getNext().getElement();
	}

	public T last()
	{
		return tail.getPrev().getElement();
	}

	public int size()
	{
		return (count);
	}

	public boolean isEmpty()
	{
		return (count == 0);
	}

	public Node<T> find(Object obj)
	{
		Node<T> temp = head.getNext();

		for (int i = 0; i < count; i++)
		{
			if (temp.equals(obj))
			{
				return temp;
			}
			temp = temp.getNext();
		}

		return null;
	}

	public String toString()
	{
		Node<T> temp = head.getNext();
		String retString = "";

		for (int i = 0; i < count; i++)
		{
			retString += temp.toString();
			temp = temp.getNext();
		}

		return retString;
	}

}
