package com.company;

public class ReverseInKGroup {

    public static void main(String[] args) {
	// write your code here
        int listSize = 100;
        int[] array;
        array = new int[listSize];

        for (int i = 0; i<array.length; ++i){
            array[i]=i;
        }

        ListNode listHead = populateList(array);
        int k = 7;

        outputList(listHead);

        listHead = Reverse(listHead, listSize, k);

        outputList(listHead);

    }



    private static NodePair ReverseNodePair(NodePair pair){
        if (pair.n2==null) return pair;
        ListNode n3 = pair.n2.next;
        pair.n2.next=pair.n1;
        NodePair newPair = new NodePair(pair.n2, n3);
        return newPair;
    }

    private static NodePair ReverseNodeGroup(NodePair pair, int k){
        ListNode previousGroupEnd = pair.n1;
        ListNode currentGroupStart = pair.n2;
        ListNode currentNode = pair.n2;

        NodePair currentPair = new NodePair(currentNode, currentNode.next);

        for (int i=1;i<k;++i){
                currentPair = ReverseNodePair(currentPair);
        }

        previousGroupEnd.next = currentPair.n1;
        currentGroupStart.next = currentPair.n2;

        return new NodePair(currentGroupStart, currentGroupStart.next);
    }

    public static ListNode Reverse(ListNode listHead, int listSize, int k){
        ListNode dummy = new ListNode(-999);
        dummy.next = listHead;

        int numNodesLeft = listSize-k;
        NodePair currentPair = new NodePair(dummy, dummy.next);

        while (numNodesLeft>=0){
            currentPair = ReverseNodeGroup(currentPair, k);
            numNodesLeft-=k;
        }

        return dummy.next;
    }

    public static void outputList(ListNode listHead){
        ListNode currentNode = listHead;
        int lineSize = 10;
        int totprinted = 0;

        while (currentNode!=null){
            System.out.printf("%d ",currentNode.val);
            currentNode = currentNode.next;
            totprinted++;
            if (totprinted % lineSize==0){
                System.out.printf("\n");
            }
        }

        if (totprinted%lineSize!=0){
            System.out.printf("\n");
        }
    }

    public static ListNode populateList(int[] array){
        ListNode dummy = new ListNode(-999);
        ListNode currentNode = dummy;
        for (int i = 0; i<array.length; ++i){
            currentNode.next = new ListNode(array[i]);
            currentNode = currentNode.next;
        }

        return dummy.next;
    }


}
