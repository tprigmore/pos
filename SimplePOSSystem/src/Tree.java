
public class Tree<T>
{
	private Node<T> root;
	private int count;

	public Tree()
	{
		root = new Node<T>();
		count = 0;
	}

	public Tree(T element)
	{
		this.root = new Node<T>(element);
		this.count = 1;
	}

	public void setRoot(Node<T> node)
	{
		this.root = node;
	}

	public Node<T> getRoot()
	{
		return root;
	}

	public void setCount(int num)
	{
		count = num;
	}

	public int getCount()
	{
		return count;
	}

	public Node<T> getParent(Node<T> node)
	{
		return node.getPrev();
	}

	public Node<T> getLeftChild(Node<T> node)
	{
		return node.getLeft();
	}

	public Node<T> getRightChild(Node<T> node)
	{
		return node.getRight();
	}

	public T getElement(Node<T> node)
	{
		return node.getElement();
	}

	public int Size()
	{
		return count;
	}

	public int Elements()
	{
		return count;
	}

	public boolean isEmpty()
	{
		return count == 0;
	}

	public boolean isRoot(Node<T> node)
	{
		return node.getPrev() == null;
	}

	public boolean isInternal(Node<T> node)
	{
		return (node.getPrev() != null) && ((node.getLeft() != null) && (node.getRight() != null));
	}

	public boolean isExternal(Node<T> node)
	{
		return (node.getLeft() == null) && (node.getRight() == null);
	}

	public boolean insert(T element)
	{
		Node<T> current = root;
		Node<T> parent = root;

		if (!(element instanceof Comparable))
			System.out.println("Element to insert is not Comparable.");

		while (current != null)
		{
			if (((Comparable<T>) current.getElement()).compareTo(element) < 0)
			{
				parent = current;
				current = current.getRight();
			}
			else if (((Comparable<T>) current.getElement()).compareTo(element) > 0)
			{
				parent = current;
				current = current.getLeft();
			}
			else
				return false;
		}

		if (((Comparable<T>) parent.getElement()).compareTo(element) > 0)
			parent.setLeft(new Node<T>(element));
		else
			parent.setRight(new Node<T>(element));

		count++;
		return true; // Element inserted

	}

	public boolean find(T element)
	{
		Node<T> current = root;

		if (!(element instanceof Comparable))
			System.out.println("Element to find is not Comparable.");

		while (current != null)
			if (((Comparable<T>) current.getElement()).compareTo(element) > 0)
			{
				current = current.getLeft();
			}
			else if (((Comparable<T>) current.getElement()).compareTo(element) < 0)
			{
				current = current.getRight();
			}
			else
				return true;
		return false;
	}

	public Node<T> findNode(T element)
	{
		Node<T> current = root;

		if (!(element instanceof Comparable))
			System.out.println("Element to find is not Comparable.");

		while (current != null)
			if (((Comparable<T>) current.getElement()).compareTo(element) > 0)
			{
				current = current.getLeft();
			}
			else if (((Comparable<T>) current.getElement()).compareTo(element) < 0)
			{
				current = current.getRight();
			}
			else
				return current;
		return null;
	}

	public T removeElement(T element)
	{

		T returnElement = null;

		if (isEmpty())
		{
			System.out.println("Tried to remove an element form empty tree.");
			return returnElement;
		}
		else
		{
			Node<T> parent = null;
			if (((Comparable<T>) element).equals(root.getElement()))
			{
				returnElement = root.getElement();
				Node<T> replacementNode = findReplacement(root);
				if (replacementNode == null)
				{
					root = null;
					count = 0;
				}
				else
				{
					root.setElement(replacementNode.getElement());
					root.setLeft(replacementNode.getLeft());
					root.setRight(replacementNode.getRight());
					count--;
				}
			}
			else
			{
				parent = root;
				if (((Comparable<T>) element).compareTo(root.getElement()) < 0)
				{
					returnElement = removeElement(element, root.getLeft(), parent);
				}
				else
				{
					returnElement = removeElement(element, root.getRight(), parent);
				}

			}

		}

		return returnElement;
	}

	public T removeElement(T element, Node<T> node, Node<T> parent)
	{

		T returnElement = null;

		if (node == null)
		{
			System.out.println("Tried to remove an element form empty tree.");
			return returnElement;
		}
		else
		{
			if (((Comparable<T>) element).equals(node.getElement()))
			{
				returnElement = node.getElement();
				Node<T> replacementNode = findReplacement(node);
				if (parent.getRight() == node)
				{
					parent.setRight(replacementNode);
				}
				else
				{
					parent.setLeft(replacementNode);
				}
				count--;
			}
			else
			{
				parent = node;
				if (((Comparable<T>) element).compareTo(node.getElement()) < 0)
				{
					returnElement = removeElement(element, node.getLeft(), parent);
				}
				else
				{
					returnElement = removeElement(element, node.getRight(), parent);
				}

			}

		}

		return returnElement;
	}

	public Node<T> findReplacement(Node<T> node)
	{
		Node<T> returnElement = null;

		if ((node.getLeft() == null) && (node.getRight() == null))
		{
			returnElement = null;
		}
		else if ((node.getLeft() != null) && (node.getRight() == null))
		{
			returnElement = node.getLeft();
		}
		else if ((node.getLeft() == null) && (node.getRight() != null))
		{
			returnElement = node.getRight();
		}
		else
		{
			Node<T> current = node.getRight();
			Node<T> parent = node;

			while (current.getLeft() != null)
			{
				parent = current;
				current = current.getLeft();
			}

			current.setLeft(node.getLeft());

			if (node.getRight() != current)
			{
				parent.setLeft(current.getRight());
				current.setRight(node.getRight());
			}
			returnElement = current;
		}
		return returnElement;
	}

	public Node<T> traversalPreOrder(Node<T> node)
	{

		if (node != null)
		{
			visitNode(node);
			traversalPreOrder(node.getLeft());
			traversalPreOrder(node.getRight());
			return node;
		}
		else
		{
			return null;
		}

	}

	public Node<T> traversalInOrder(Node<T> node)
	{

		if (node != null)
		{
			traversalInOrder(node.getLeft());
			visitNode(node);
			traversalInOrder(node.getRight());
			return node;
		}
		else
		{
			return null;
		}

	}

	public Node<T> traversalPostOrder(Node<T> node)
	{

		if (node != null)
		{
			traversalPostOrder(node.getLeft());
			traversalPostOrder(node.getRight());
			visitNode(node);
			return node;
		}
		else
		{
			return null;
		}

	}

	public void visitNode(Node<T> node)
	{
		System.out.print(" " + node.getElement().toString());
	}

	public T getMax()
	{
		Node<T> current = root;

		while (current.getRight() != null)
		{
			current = current.getRight();
		}

		return current.getElement();
	}
}