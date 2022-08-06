package Data;

public class Heap {

	private HNode[] heapArray;
	private int currentSize;
	
	public static void main(String[] args) {
		Heap newHeap = new Heap(10);
		newHeap.insert(10);
		newHeap.insert(12);
		newHeap.insert(42);
		newHeap.insert(35);
		newHeap.insert(16);
		newHeap.insert(88);
		newHeap.insert(42);
		newHeap.insert(7);
		newHeap.remove();
		newHeap.remove();
		
		newHeap.displayHeap();

	}
	public Heap(int maxSize){
		heapArray=new HNode[maxSize];
		this.currentSize=0;
	}

	public void insert(int key){
		HNode newNode=new HNode(key);
		heapArray[currentSize]=newNode;
		currentSize++;
		swapUp(newNode);
		
	}
	
	private void swapUp(HNode newNode) {
		int idx=currentSize-1;
		int parentIdx;
		while(idx>0){
			parentIdx=(idx-1)/2;
			if(newNode.getKey()>heapArray[parentIdx].getKey()){
				heapArray[idx]=heapArray[parentIdx];
				idx=parentIdx;
			}else{
				break;
			}
		}
		heapArray[idx]=newNode;
	}
	
	public HNode remove(){
		HNode nodeToRemove=heapArray[0];
		heapArray[0]=heapArray[currentSize-1];
		currentSize--;
		swapDown();
		return nodeToRemove;
	}
	
	private void swapDown() {
		HNode swapNode=heapArray[0];
		int idx=0,lIdx,rIdx, bigChild;
		while(idx<currentSize/2){
			lIdx=2*idx+1;
			rIdx=2*idx+2;
			if(rIdx<currentSize && heapArray[rIdx].getKey()>heapArray[lIdx].getKey()){
				bigChild=rIdx;
			}else{
				bigChild=lIdx;
			}
			if(swapNode.getKey()<heapArray[bigChild].getKey()){
				heapArray[idx]=heapArray[bigChild];
				idx=bigChild;
			}else{
				break;
			}
		}
		heapArray[idx]=swapNode;
	}
	
	public void displayHeap() {
        System.out.println("Data.Heap Array: ");
        for(int m = 0; m < currentSize; m++) {
            if(heapArray[m] != null) {
                System.out.print( heapArray[m].getKey() + " ");
            }
            System.out.println();
            
            int nBlanks = 32;
            int itemsPerRow = 1;
            int column = 0;
            int j = 0; // current item
            
            String dots = "...............................";
            System.out.println(dots+dots);
            while(currentSize > 0) {
                if(column == 0) {
                    for(int k = 0; k < nBlanks; k++) {
                        System.out.print(" ");
                    }
                }
                System.out.print(heapArray[j].getKey());
                j++;
                if(j == currentSize) {
                    break;
                }
                
                column++;
                // end of row
                if(column == itemsPerRow) {
                    nBlanks = nBlanks/2; // half the blanks
                    itemsPerRow = itemsPerRow * 2;     // twice the items
                    column = 0;
                    System.out.println();
                } else {
                    for(int k=0; k<nBlanks*2; k++) {
                        System.out.print(" ");
                    }
                }
                    
            }
            System.out.println("\n"+dots+dots);
        }
	}
}

class HNode {
	private int key;

	public HNode(int key) {
		super();
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

}