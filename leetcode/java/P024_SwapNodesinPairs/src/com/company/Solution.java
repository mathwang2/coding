package com.company;

class Solution {
    public static ListNode swapPairs(ListNode head) {
        ListNode first, second, third, pre=null;
        first = head;

        while (first!=null) {
            second = first.next;
            if (second==null) break;
            third = second.next;
            if (pre!=null) pre.next = second;
            else head = second;
            first.next=third;
            second.next=first;
            pre=first;
            first=third;
        }

        return head;


    }
}
