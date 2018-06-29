package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] nums = {1,2,3,4,5};
        int k=2;
        ListNode head = new ListNode(nums[0]);
        ListNode curr = head;
        for (int i=1; i<nums.length; ++i){
            curr.next = new ListNode(nums[i]);
            curr = curr.next;
        }

        ListNode nh = Solution.rotateRight(head, k);
        while (nh!=null){
            System.out.printf("%d ", nh.val);
            nh=nh.next;
        }

    }
}
