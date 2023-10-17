package no.ntnu.idata2302.lab04;

import java.util.ArrayList;
/**
 * This class represents a minimum heap
 */
public class Heap {
	
	public static Heap fromValues(int... values) {
		var heap = new Heap();
		for (var each : values) {
			heap.insert(each);
		}
		return heap;
	}

	private ArrayList<Integer> array;

	public Heap() {
		array = new ArrayList<Integer>();
		
	}

	/**
	 * homie.
	 * 
	 * @param k
	 */
	public void insert(Integer k) {
		array.add(k);
		int pos = array.size() - 1;


		heapify(pos);

	}

	public void heapify (int pos) {

		if (pos == 0) {
			return;
		}

		int parentPos = parentOf(pos);

		if ( array.get(pos) < array.get(parentPos)) {
			swap(pos, parentOf(pos));

			heapify(parentOf(pos));
		}
	}

	/**
	 * Returns the smallest item
	 * 
	 * @return the smallest item in the heap
	 */
	public int takeMinimum() {
		int minimum = array.get(0);

		delete(0);

		return minimum;
	}

	public void delete(int pos) {
		swap(pos, array.size()-1);
		array.remove(array.size()-1);
		bubbleDown(pos);
	} 

	public void bubbleDown(int pos){
		int left = leftChildOf(pos);
		int right = rightChildOf(pos);

		if (right > array.size() -1 ) {
			return;
		}

		if (array.get(pos) < array.get(left) && array.get(pos) < array.get(right)) {
			return;
		} else {
			if (array.get(left) < array.get(right)) {
				swap(pos, left);
				bubbleDown(left);
			} else {
				swap(pos, right);
				bubbleDown(right);
			}
		}
	}


	public void decreaseKey(int i, int k) {
		// TODO: Implement this operation
		throw new RuntimeException("Not yet implemented");
	}

	/**
	 * returns the parent of a given position 
	 * 
	 * @param index 
	 * @return the parent of the index
	 */
	private int parentOf(int index) {
		return index / 2;
	}

	private int leftChildOf(int index) {
		return index * 2;
	}
	
	private int rightChildOf(int index) {
		return index * 2 + 1;
	}

	/**
	 * Swaps the placement the items at pos1 and pos2 
	 * 
	 * @param pos1
	 * @param pos2
	 */
	void swap(int pos1, int pos2) {
		int temp = array.get(pos1);
		array.set(pos1, array.get(pos2));
		array.set(pos2, temp);
	}
}
