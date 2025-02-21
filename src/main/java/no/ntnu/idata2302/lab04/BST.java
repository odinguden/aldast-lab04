package no.ntnu.idata2302.lab04;

import java.util.ArrayList;

public class BST {

	public static BST fromValues(int... values) {
		if (values.length < 1)
			throw new IllegalArgumentException("A binary search tree must have at least one value");
		if (values.length == 1)
			return new BST(values[0]);
		var tree = new BST(values[0]);
		for (int i = 1; i < values.length; i++) {
			tree.insert(values[i]);
		}
		return tree;
	}

	private int value;
	private BST left;
	private BST right;

	public BST(int value) {
		this.value = value;
	}

	public BST insert(int givenValue) {
		if (givenValue == value) {
			throw new IllegalArgumentException("Cannot insert a duplicate value");
		} else if (givenValue < value) {
			if (this.hasLeft()) {
				this.left.insert(givenValue);
			} else {
				this.left = new BST(givenValue);
			}
		} else {
			if (this.hasRight()) {
				this.right.insert(givenValue);
			} else {
				this.right = new BST(givenValue);
			}
		}

		// What do you want us to return
		return this;
	}

	/**
	 * @return the number of items in this tree
	 */
	public int size() {
		int size = 1;
		if (hasLeft()) {
			size += this.left.size();
		}
		if (hasRight()) {
			size += this.right.size();
		}
		return size;
	}

	private boolean hasLeft() {
		return left != null;
	}

	private boolean hasRight() {
		return right != null;
	}

	int minimum() {
		if (this.hasLeft()) {
			return this.left.minimum();
		} else {
			return this.value;
		}
	}

	int maximum() {
		if (this.hasRight()) {
			return this.right.maximum();
		} else {
			return this.value;
		}

	}

	boolean contains(int givenValue) {
		if (value < givenValue) {
			if (hasRight()) {
				return right.contains(givenValue);
			}
			return false;

		} else if (value > givenValue) {
			if (hasLeft()) {
				return left.contains(givenValue);
			}
			return false;

		} else {
			return true;

		}
	}

	int successor(int givenValue) {
		if (value < givenValue) {
			if (hasRight()) {
				return right.successor(givenValue);

			}
			throw new NoSuchValue(givenValue);

		} else if (value > givenValue) {
			if (hasLeft()) {
				try {
					return left.successor(givenValue);

				} catch (SuccessorNotFound error) {
					return value;

				} catch (NoSuchValue error) {
					return value;

				}
			}
			throw new NoSuchValue(givenValue);

		} else {
			if (hasRight()) {
				return right.minimum();

			}
			throw new SuccessorNotFound();
		}
	}

	BST delete(int givenValue) {
		if (givenValue < value) {
			if (hasLeft()) {
				left = left.delete(givenValue);
				return this;
			}
			throw new NoSuchValue(givenValue);
		} else if (givenValue > value) {
			if (hasRight()) {
				right = right.delete(givenValue);
				return this;
			}
			throw new NoSuchValue(givenValue);
		} else {
			if (hasLeft() && !hasRight()) {
				return left;
			}
			if (!hasLeft() && hasRight()) {
				return right;
			}
			if (hasLeft() && hasRight()) {
				try {
					value = successor(value);

				} catch (SuccessorNotFound error) {
					return null;

				}
				right.delete(value);
				return this;
			}
			return null;
		}
	}

	public String format() {
		String retval = "";
		
		if (this.hasLeft()) {
			retval += this.left.format() + ", ";
		}
		retval += this.value;
		if (this.hasRight()) {
			retval += ", " + this.right.format();
		}
		
		return retval;
	}

}

class NoSuchValue extends RuntimeException {

	private int value;

	public NoSuchValue(int givenValue) {
		super();
		this.value = givenValue;
	}
}

class SuccessorNotFound extends RuntimeException {
}

class PredecessorNotFound extends RuntimeException {
}
