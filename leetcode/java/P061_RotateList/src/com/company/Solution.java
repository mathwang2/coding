package com.company;

public class Solution {
    public static ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null||k==0) return head;

        int cnt = 1;
        ListNode last = head;
        while (last.next!=null){
            cnt++;
            last=last.next;
        }

        k%=cnt;

        if (k==0) return head;

        int pos = cnt-k;

        int i=0;
        ListNode nhp = head, nh;
        while (i<pos-1){
            nhp=nhp.next;
            ++i;
        }

        nh = nhp.next;

        last.next=head;
        head=nh;
        nhp.next=null;
        return head;
    }
}
