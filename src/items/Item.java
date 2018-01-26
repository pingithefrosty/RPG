package items;

import characters.Hero;

public abstract class Item implements Comparable<Item>{
	private int id;
	private String name;
	private int value;
	
	private static int COUNTER=0;
	
	public Item(String name) {
		this.name=name;
		this.id=COUNTER++;
		// TODO deal with value
	}
	
	abstract public void use(Hero h);

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " +  name + "[" + id +"]";
	}

	@Override
	public int compareTo(Item arg0) {
		return new Integer(id).compareTo(arg0.id);
	}
	
	
	
}
