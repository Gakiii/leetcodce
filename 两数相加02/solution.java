/*给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，
并且它们的每个节点只能存储 一位 数字。
如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
您可以假设除了数字 0 之外，这两个数都不会以 0 开头

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807*/

/*
思路：
1.建立一个新链表来存储结果；
2.同时遍历两个给定的链表，如果两个链表都有值则相加，取个位数作为新链表的值，＞=10的时候，进位up置为1
3.当一个链表遍历完，另一个没有遍历完的时候，将遍历完数组的后续所有值假象为0，再将没有遍历完的数组遍历完
4.当两个数组同时完毕的时候，检查进位，进位为1则再最末尾加上一个节点，节点内容为1
*/

//代码：刚刚开始写 可能有些语法层面有些啰嗦


class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode point1 = l1;
        ListNode point2 = l2;
        ListNode answer = new ListNode(0);
        ListNode point3 = answer;
        /*将两个链表都遍历到最后*/
        while (point1.next != null) {
            point1 = point1.next;
        }
        while (point2.next != null) {
            point2 = point2.next;
        }

        if (point1.val == 0 && point2.val == 0) {
            return new ListNode(0);
        }

        if (point1.val == 0 && point2.val != 0) {
            return l2;
        }

        if (point1.val != 0 && point2.val == 0) {
            return l1;
        }
        int up = 0;//up表示进位首先设置为0，表示没有进位
        point1 = l1;
        point2 = l2;//将指针移回到首部
        while (point1 != null && point2 != null) //当两个链表都有值的时候，相加并且算是否有进位
        {
            int newnumber = (point1.val + point2.val + up) % 10;
            point3.next = new ListNode(newnumber);
            if (point1.val + point2.val+up >= 10) up = 1;
            else up = 0;
            point1 = point1.next;
            point2 = point2.next;
            point3 = point3.next;
        }
        if (point1 == null&&point2!=null) {
            while (point2 != null) {
                int newnumber = (point2.val + up) % 10;
                point3.next = new ListNode(newnumber);
                if (point2.val + up >= 10) up = 1;
                else up = 0;
                point2 = point2.next;
                point3 = point3.next;
            }
            if(up==1)
            {
                point3.next=new ListNode(1);
                point3=point3.next;
                up=0;
            }
        }
        if (point2 == null&&point1!=null) {
            while (point1 != null) {
                int newnumber = (point1.val + up) % 10;
                point3.next = new ListNode(newnumber);
                if (point1.val + up >= 10) up = 1;
                else up = 0;
                point1 = point1.next;
                point3 = point3.next;
            }
            if(up==1)
            {
                point3.next=new ListNode(1);
                point3=point3.next;
                up=0;
            }
        }

        if(point1==null&&point2==null)
        {
            if(up==1)
                point3.next = new ListNode(1);
            point3=point3.next;
            up=0;

        }
        answer = answer.next;
        return answer;

    }
}