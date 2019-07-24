package circularDoublyLinkedList;

public class List {
	
	Node head, tail;
	public List() {
		this.head = this.tail = null;
	}
	public boolean isEmpty() {
		return this.head == null;
	}
	public void printAll() {
		if(this.isEmpty())
			System.out.println();
		else {
			Node tmp = this.head;
			do {
				System.out.print(tmp.info + " ");
				tmp = tmp.next;
			}while(tmp != this.head);
			System.out.println();
		}
	}
	public int size() {
		if(this.isEmpty())
			return 0;
		else {
			Node tmp = this.head;
			int count = 0;
			do {
				count++;
				tmp = tmp.next;
			} while (tmp != this.head);
			return count;
		}
	}
	public void addToHead(int i) {
		if(this.isEmpty()) {
			this.head = this.tail = new Node(i);
			this.head.prev = this.tail;
			this.tail.next = this.head;
		}
		else {
			this.head = new Node(i, this.tail, this.head);
			this.head.next.prev = this.head;
			this.tail.next = this.head;
		}
	}
	public void addToTail(int i) {
		if(this.isEmpty()) {
			this.head = this.tail = new Node(i);
			this.head.prev = this.tail;
			this.tail.next = this.head;
		}
		else {
			this.tail = new Node(i, this.tail, this.head);
			this.tail.prev.next = this.tail;
			this.head.prev = this.tail;
		}
	}
	public Integer deleteFromHead() {
		if(this.isEmpty())
			return null;
		else {
			Integer toReturn = this.head.info;
			if(this.head == this.tail)
				this.head = this.tail = null;
			else {
				this.head = this.head.next;
				this.head.prev = this.tail;
				this.tail.next = this.head;
			}
			return toReturn;
		}
	}
	public Integer deleteFromTail() {
		if(this.isEmpty())
			return null;
		else {
			Integer toReturn = this.tail.info;
			if(this.head == this.tail)
				this.head = this.tail = null;
			else {
				Node tmp;
				for(tmp = this.head; tmp.next != this.tail; tmp = tmp.next);
				this.tail = tmp;
				this.tail.next = this.head;
				this.head.prev = this.tail;
			}
			return toReturn;
		}
	}
	public Integer deleteNodesWithValue(int value) {
		Integer toReturn = null;
		if(this.isEmpty()) {
			System.out.println("List is empty");
		}
		else {
			Node current = this.head;
			while(current.next != this.head) {
				if(current.next.payload == value) {
					toReturn = current.next.payload;
					current.next = current.next.next;
					current.next.prev = current;
				}
				else
					current = current.next;
			}
			this.tail = current;
			this.tail.next = this.head;
			this.head.prev = this.tail;
		}
		if(this.head.payload == value)
			toReturn = this.deleteFromHead();
		return toReturn;
	}
	
	public void deleteOnIndex(int index) {
		int size = this.size();
		if(index < 0 || index >= size)
			System.out.println("Index out of range");
		else {
			if(index == 0)
				this.deleteFromHead();
			else if(index == size - 1)
				this.deleteFromTail();
			else {
				int count = 0;
				Node tmp = this.head;
				while(count < index) {
					tmp = tmp.next;
					count++;
				}
				tmp.prev.next = tmp.next;
				tmp.next.prev = tmp.prev;
			}
		}
	}
	
	public void insertBefore(int listElement, int newElement) {
		if(this.isEmpty())
			System.out.println("List is empty");
		else {
			Node tmp = this.head;
			do {
				if(tmp.info == listElement) {
					if(tmp == this.head)
						this.addToHead(newElement);
					else {
						Node newNode = new Node(newElement, tmp.prev, tmp);
						tmp.prev.next = newNode;
						tmp.prev = newNode;
					}
				}
				tmp = tmp.next;
			}while(tmp != this.head);
		}
	}
	
	public void insertAfer(int listElement, int newElement) {
		if(this.isEmpty())
			System.out.println("List is empty");
		else {
			Node tmp = this.head;
			do {
				if(tmp.info == listElement) {
					if(tmp == this.tail)
						this.addToTail(newElement);
					else {
						tmp.next = new Node(newElement, tmp, tmp.next);
						tmp.next.next.prev = tmp.next;
					}
					tmp = tmp.next;
				}
				tmp = tmp.next;
			}while(tmp != this.head);
		}
	}
	
	public void sort() {
		if(this.isEmpty())
			System.out.println("List is empty");
		else {
			for(Node outer = this.head; outer.next != this.head; outer = outer.next) {
				for(Node inner = this.tail; inner != outer; inner = inner.prev) {
					if(inner.prev.info > inner.info) {
						int storage = inner.prev.info;
						inner.prev.info = inner.info;
						inner.info = storage;
					}
				}
			}
		}
	}

}
