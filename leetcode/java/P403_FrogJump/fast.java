class Solution {
    public boolean canCross(int[] stones) {
        if(stones[1] != 1){
            return false;
        }
        for(int i = 0; i< stones.length - 1;i++){ // just for improvment
            if(stones[i]+stones[i]+1 < stones[i+1]){
                return false;
            }
        }
        return canCross(stones, 1,1);
    }
    public boolean canCross(int[] stones, int k, int pos){
        int curr = stones[pos];
        int last = stones[stones.length-1];
        if(k <= 0  || curr +k-1 >last){
            return false;
        }
        if(Math.abs(last - curr -k) <= 1){
            return true;
        }
        // jump k+1
        pos = Arrays.binarySearch(stones, curr+k+1);
        if(pos >= 0 && canCross(stones, k+1, pos)){
            return true;
        }
        //jump k
        pos = Arrays.binarySearch(stones, curr+k);
        if(pos >= 0 && canCross(stones, k, pos)){
            return true;
        }
        //jump k-1
        pos = Arrays.binarySearch(stones, curr+k-1);
        if(pos>=0 && canCross(stones, k-1, pos)){
            return true;
        }
        return false;
    }
}