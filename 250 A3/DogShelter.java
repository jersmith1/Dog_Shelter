package assignment3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class DogShelter implements Iterable<Dog> {
	public DogNode root;

	public DogShelter(Dog d) {
		this.root = new DogNode(d);
	}

	private DogShelter(DogNode dNode) {
		this.root = dNode;
	}

	// add a dog to the shelter
	public void shelter(Dog d) {
		if (root == null) 
			root = new DogNode(d);
		else
			root = root.shelter(d);
	}

	// removes the dog who has been at the shelter the longest
	public Dog adopt() {
		if (root == null)
			return null;

		Dog d = root.d;
		root =  root.adopt(d);
		return d;
	}
	
	// overload adopt to remove from the shelter a specific dog
	public void adopt(Dog d) {
		if (root != null)
			root = root.adopt(d);
	}

	// get the oldest dog in the shelter
	public Dog findOldest() {
		if (root == null)
			return null;
		
		return root.findOldest();
	}

	// get the youngest dog in the shelter
	public Dog findYoungest() {
		if (root == null)
			return null;
		
		return root.findYoungest();
	}
	
	// get dog with highest adoption priority with age within the range
	public Dog findDogToAdopt(int minAge, int maxAge) {
		return root.findDogToAdopt(minAge, maxAge);
	}

	// Returns the expected vet cost the shelter has to incur in the next numDays days
	public double budgetVetExpenses(int numDays) {
		if (root == null)
			return 0;
		
		return root.budgetVetExpenses(numDays);
	}
	
	// returns a list of list of Dogs. The dogs in the list at index 0 need to see the vet in the next week. 
	// The dogs in the list at index i need to see the vet in i weeks. 
	public ArrayList<ArrayList<Dog>> getVetSchedule() {
		if (root == null)
			return new ArrayList<ArrayList<Dog>>();
			
		return root.getVetSchedule();
	}

	public Iterator<Dog> iterator() {
		return new DogShelterIterator();
	}

	public class DogNode {
		public Dog d;
		public DogNode younger;
		public DogNode older;
		public DogNode parent;

		public DogNode(Dog d) {
			this.d = d;
			this.younger = null;
			this.older = null;
			this.parent = null;
		}
//**************************HELPER START****************************
		/*private DogNode upHeap() {
			return null;
		}

		private DogNode rotateRight(DogNode node)
		{

			//System.out.println(node.younger.toString());
			//System.out.println(node.older.toString());
			//DogNode pivot = node.younger;
			//***

			//***
			//System.out.println(pivot.toString());
			/*if (node.) {
				node.younger = pivot.older;
				pivot.older = node;
			}
			else{
				//pivot.shelter(node.d);
				node.older = node;
				node.younger = null;
			}

			//node = pivot;

			//return pivot;
		}
		private DogNode rotateLeft(DogNode node)
		{
			DogNode pivot = node.older;
			node.older = pivot.younger;
			pivot.younger = node;
			//root = pivot;

			return pivot;
		}*/
		/*private void rotateLeft(DogNode node){
			//DogNode node = this;
			DogNode pivot = node.older;
			if(pivot.younger != null){
				pivot.younger.parent = node;
			}
			pivot.parent = node.parent;
			if(node.parent == null){
				root = pivot;
			}
			else if(node == node.parent.younger){
				node.parent.younger = pivot;
			}
			else{
				node.parent.younger = pivot;
				pivot.younger = node;
				node.parent = pivot;
			}
		}

		private void rotateRight(DogNode node){
			//DogNode node = this;
			//System.out.println("moving on");
			//System.out.println("moving on");
			DogNode pivot = node.younger;
			if (pivot.older != null){
				pivot.older.parent = node;
			}
			pivot.parent = node.parent;
			if (node.parent == null){
				root = pivot;
			}
			else if (node == node.parent.older){
				node.parent.older = pivot;
			}
			else{
				node.parent.younger = pivot;
				pivot.older = node;
				node.parent = pivot;
			}
		}
*/
		/*private DogNode bubbleUp(DogNode x){
			//DogNode x = this;
			if (x.parent == null){
				//System.out.println(x.equals(root));
				return x;
			}
			if (x.parent != null && x.d.getDaysAtTheShelter() < x.parent.d.getDaysAtTheShelter()){
				return root;
			}
			if (x == x.parent.younger){
				if (x.younger!=null) {
					rightRotation(x);
				}
				else{
					DogNode holder = x;
					DogNode pOfP = x.parent.parent;
					//System.out.println(holder.toString());
					//System.out.println("moving on");
					x = x.parent;
					x.parent = holder;
					x.younger = null;
					x.older = null;
					x.parent.parent = pOfP;
					System.out.println(x.toString());
					System.out.println("moving on");
					System.out.println(x.parent.toString());
					System.out.println("moving on");
					x.parent.younger = x;
					DogNode temp = x.parent;
					x.parent = x;
					//x = temp;
					x.parent.older = temp;
					temp.parent = x.parent;
					x.parent.younger = null;
					return root;
				}
			}
			else{
				if (x.older != null) {
					leftRotation(x);
				}
				else{
					DogNode temp = x.parent;
					x.parent = x;
					x.parent.younger = temp;
					x.parent.older = null;
					return root;
				}
			}
			return bubbleUp(x);
		}

		private void leftRotation(DogNode u) {
			DogNode w = u.older;
			w.parent = u.parent;
			if (w.parent != null) {
				if (w.parent.younger == u) {
					w.parent.younger = w;
				} else {
					w.parent.older = w;
				}
			}
			u.older = w.younger;
			if (u.older != null) {
				u.older.parent = u;
			}
			u.parent = w;
			w.younger = u;
			if (u == root) { root = w; root.parent = null; }
		}
		private void rightRotation(DogNode u) {
			DogNode w = u.younger;
			w.parent = u.parent;
			if (w.parent != null) {
				if (w.parent.younger == u) {
					w.parent.younger = w;
				} else {
					w.parent.older = w;
				}
			}
			u.younger = w.older;
			if (u.younger != null) {
				u.younger.parent = u;
			}
			u.parent = w;
			w.older = u;
			if (u == root) { root = w; root.parent = null; }
		}*/
		private DogNode rotateRight() {
			DogNode X = this.younger;
			DogNode Y = this.younger.older;
			X.older = this;
			X.parent = this.parent;
			this.younger = Y;
			this.parent = X;

			if (Y != null) {
				Y.parent = this;
			}

			return X;
		}
		private DogNode rotateLeft() {
			DogNode W = this.older;
			DogNode Z = this.older.younger;
			W.younger = this;
			W.parent = this.parent;
			this.parent = W;
			this.older = Z;

			if (Z != null) {
				Z.parent = this;
			}

			return W;
		}
		//**************************HELPER END****************************
// new and improved shelter code starts
		//BST search + move up?? + rotations
		public DogNode shelter(Dog d) {
			DogNode cur;
			cur = this;

			if (d.getAge() < this.d.getAge()) {	//bst insertion search
				if (this.younger == null) {
					this.younger = new DogNode(d);
					this.younger.parent = this;
				}
				else {
					this.younger = this.younger.shelter(d);
				}

				if (this.younger != null && this.younger.d.getDaysAtTheShelter() > this.d.getDaysAtTheShelter()) { //check for rot
					cur = this.rotateRight();
				}
			}
			else {
				if (this.older == null) {
					this.older = new DogNode(d);
					this.older.parent = this;
				}
				else {
					this.older = this.older.shelter(d);
				}
				if (this.older != null && this.older.d.getDaysAtTheShelter() > this.d.getDaysAtTheShelter()) { //checks if a tree rotation is needed
					cur = this.rotateLeft();
				}
			}
			return cur;
		}
		//LFGGGGGGGGGGGG
// new and improved shelter code ends here
//OG SHELTER CODE BEGINS HERe
		/*public DogNode shelter (Dog d) {
			DogNode z = new DogNode(d);
			DogNode y = null;
			DogNode x = root;

			while (x != null){
				y = x;
				if (z.d.getAge()<x.d.getAge()){
					x = x.younger;
				}
				else{
					x = x.older;
				}
			}
			z.parent = y;
			if(y == null){
				root = z;
			}
			else if(z.d.getAge()<y.d.getAge()){
				y.younger = z;
			}
			else{
				y.older = z;
			}
			root = bubbleUp(z);

            return root; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}*/
//******************************my extra code******************************
		private ArrayList<Dog> buildArray(ArrayList<Dog> dogArr) {	//visits each node in the tree and adds them to the arraylist
			//tree traversal to add each assignment3.Dog element of the tree to an non linked unsorted arraylist
			if (this.d != null) {
				dogArr.add(this.d);
			}
			if (this.younger != null) {
				this.younger.buildArray(dogArr);
			}
			if (this.older != null) {
				this.older.buildArray(dogArr);
			}
			return dogArr;
		}

		private ArrayList<Dog> merge(ArrayList<Dog> list1, ArrayList<Dog> list2) {	//non recursive
			ArrayList<Dog> merged = new ArrayList<>();
			while (!list1.isEmpty() && !list2.isEmpty()) {
				if (list1.get(0).getAge() < list2.get(0).getAge()) {
					merged.add(list1.remove(0));
				}
				else {
					merged.add(list2.remove(0));
				}
			}
			while (!list1.isEmpty()) {
				merged.add(list1.remove(0));
			}
			while (!list2.isEmpty()) {
				merged.add(list2.remove(0));
			}
			//System.out.println(merged.toString());
			return merged;
		}

		private ArrayList<Dog> ageMergeSort(ArrayList<Dog> ageList) {
			//System.out.println(ageList.toString());
			if (ageList.size() == 1) {
				return ageList;
			}
			else {
				int mid = (ageList.size()-1)/2;
				ArrayList<Dog> list1 = new ArrayList<>();
				ArrayList<Dog> list2 = new ArrayList<>();
				for (int i = 0; i < mid; i++) {
					list1.add(ageList.get(i));
				}
				//System.out.println(list1.toString());
				for (int j = mid; j < ageList.size(); j++) {
					list2.add(ageList.get(j));
				}
				//System.out.println(list2.toString());
				//this.ageMergeSort(list1);
				//this.ageMergeSort(list2);
				return merge(list1, list2);
			}
		}
	/*	private DogNode treeSearch(Dog d){
			if (this == null || d == root.d){
				return this;
			}
			if (d.getAge() < this.d.getAge()){
				return this.younger.treeSearch(d);
			}
			else{
				return this.older.treeSearch(d);
			}
		}
		private DogNode bubbleDown(DogNode x){
			if (x.younger == null && x.older == null){
				return root;
			}
			if (x.younger != null && x.older != null){
				if (x.younger.d.getDaysAtTheShelter() < x.older.d.getDaysAtTheShelter()){
					rightRotation(x);
				}
				else{
					leftRotation(x);
				}
			}
			else if (x.younger != null){
				rightRotation(x);
			}
			else{
				leftRotation(x);
			}
			return bubbleDown(x);
		}*/
//**************************my extra code ends here**************************************
		public DogNode adopt(Dog d) {
			DogNode cur;
			cur = this;

			//System.out.println(cur.toString());

			if (d == null) {
				return this;
			}
			if (d.getAge() < this.d.getAge()) {
				if (this.younger != null) {
					this.younger = this.younger.adopt(d);
				}
			}
			else if (d.getAge() > this.d.getAge()) {
				if (this.older != null) {
					this.older = this.older.adopt(d);
				}
			}
			else if (this.d.equals(d)) { //d found and equivalent
				if (this.younger == null && this.older == null) {
					cur = null;
				}
				else if (this.younger != null && this.older != null) {
					if (cur.younger.d.getDaysAtTheShelter() < cur.older.d.getDaysAtTheShelter()) {
						cur = cur.rotateLeft();
						cur.younger = cur.younger.adopt(d);
					}
					else {
						cur = cur.rotateRight();
						cur.older = cur.older.adopt(d);
					}
				}
				else {
					if (this.younger == null) {
						this.older.parent = this.parent;
						cur = this.older;
					}
					else {
						this.younger.parent = this.parent;
						cur = this.younger;
					}
				}
			}
			return cur;
		}
			/*DogNode x = this.treeSearch(d);
			bubbleDown(x);
			if(x == x.parent.younger){
				x.parent.younger = null;
			}
			else{
				x.parent.older = null;
			}
			//find the dog --- tree traversal
			//if has both children
				//dog = dog.older
				//remove dog.older (from left subtree)
			//fix tree*/

		public Dog findOldest() {
			Dog oldest = this.d;
			if (this.older != null) {
				oldest = this.older.d;
				this.older.findOldest();
			}
            // ADD YOUR CODE HERE
			//same implementation for find oldest but the reverse
            return oldest; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}

		public Dog findYoungest() {
            // ADD YOUR CODE HERE
			Dog youngest = this.d;
			if (this.younger != null) {
				youngest = this.younger.d;
				this.younger.findYoungest();
			}
			//set a variable curr youngest to be nothing
			//tree traversal
			//for first element set it to the youngest
			//visit the next element and if its younger tha curr youngest reset curr youngest to be the visited node
			//return curr youngest
            return youngest; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}

		public Dog findDogToAdopt(int minAge, int maxAge) {
			//ArrayList<assignment3.Dog> dogArr = new ArrayList<>();
			if (this.d != null && this.d.getAge()<=maxAge && this.d.getAge()>=minAge) {
				return this.d;
			}
			else if (this.d.getAge() > maxAge && this.younger != null) {
				return this.younger.findDogToAdopt(minAge, maxAge);
			}
			else if (this.d.getAge() < minAge && this.older != null) {
				return this.older.findDogToAdopt(minAge, maxAge);
			}
            // ADD YOUR CODE HERE
			//go through heap level priorities
			//check if the prioritized dogs are in the age range
			//if in range return
			//if not in range do it again
            return null; //the if statements should capture all dogs that are in the range meaning if a dog isn't caught, it isn't in range
		}
		
		public double budgetVetExpenses(int numDays) {
			double cost = 0.0;
			ArrayList<Dog> weird = new ArrayList<>();
			ArrayList<Dog> list = this.buildArray(weird);
			//System.out.println(list.toString());
			for(Dog d : list) {
				if(d.getDaysToNextVetAppointment()<=numDays) {
					cost+=d.getExpectedVetCost();
				}
			}
            // ADD YOUR CODE HERE
			//for each element in the tree += getExpectedVetCost !!!has to be within x-days !!!
			//tree traversal
			//recursive functions possible if variable set inside and then used in sum???
            return cost; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}

		public ArrayList<ArrayList<Dog>> getVetSchedule() {
			ArrayList<ArrayList<Dog>> schedule = new ArrayList<ArrayList<Dog>>();
			ArrayList<Dog> bank = new ArrayList<>();
			bank = this.buildArray(bank);
			//System.out.println(bank.toString());
			int numOfWeeks = 0;
			for (Dog d : bank) {
				if (d.getDaysToNextVetAppointment()/7>numOfWeeks) {
					numOfWeeks = d.getDaysToNextVetAppointment()/7;
				}
			}
			for (int i =0; i<numOfWeeks+1; i++) {
				schedule.add(new ArrayList<Dog>());
			}

			DogShelterIterator iter = new DogShelterIterator();
			while (iter.hasNext()) {
				Dog hendrix = iter.next();
				int index = hendrix.getDaysToNextVetAppointment()/7;
				ArrayList<Dog> inner = schedule.get(index);
				inner.add(hendrix);
				schedule.set(index, inner);
			}
            // ADD YOUR CODE HERE
			//sorting algorithm - use age-merge-sort implemented above
			//returns a 2D array list where lists of vet schedules inner sorted by age*/
			//System.out.println(schedule.toString());
            return schedule; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}

		public String toString() {
			String result = this.d.toString() + "\n";
			if (this.younger != null) {
				result += "younger than " + this.d.toString() + " :\n";
				result += this.younger.toString();
			}
			if (this.older != null) {
				result += "older than " + this.d.toString() + " :\n";
				result += this.older.toString();
			}
			if (this.parent != null) {
				result += "parent of " + this.d.toString() + " :\n";
				result += this.parent.d.toString() +"\n";
			}
			return result;
		}
		
	}

	private class DogShelterIterator implements Iterator<Dog> {
		// HERE YOU CAN ADD THE FIELDS YOU NEED
		DogNode cur = root;
		ArrayList<DogNode> arr = new ArrayList<DogNode>();

		private DogShelterIterator() {
			while (cur != null) {
				arr.add(cur);
				cur = cur.younger;
			}
			//YOUR CODE GOES HERE
		}

		public Dog next(){
            //YOUR CODE GOES HERE
			if (arr.size() == 0){
				throw new NoSuchElementException();
			}
			DogNode temp = arr.remove(arr.size()-1);
			Dog nextDog = temp.d;
			if (temp.older != null) {
				temp = temp.older;
				while (temp != null) {
					arr.add(temp);
					temp = temp.younger;
				}
			}
            return nextDog; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE*/
		}

		public boolean hasNext() {
            //YOUR CODE GOES HERE
			//do we need to check if there exists and older and a younger
            return (arr.size() != 0); // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}

	}
}
