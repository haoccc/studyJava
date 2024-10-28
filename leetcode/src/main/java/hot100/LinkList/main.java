package hot100.LinkList;

import java.util.*;

public class main {

    /**
     * 160. 相交链表
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = listLength(headA);
        int lenB = listLength(headB);

        int n = lenA - lenB;
        if (n >= 0){
            while (n > 0){
                headA = headA.next;
                n --;
            }
        } else {
            while (n < 0){
                headB = headB.next;
                n ++;
            }
        }

        while (headA != null && headB != null){
            if (headA == headB){
                return headA;
            } else {
                headA = headA.next;
                headB = headB.next;
            }
        }
        return null;
    }

    private int listLength(ListNode head){
        int len = 0;
        while (head != null){
            len ++;
            head = head.next;
        }
        return len;
    }

    /**
     *206. 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode pre = null;
        ListNode cur = head;

        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return cur;
    }

    /**
     * 234. 回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }

        int left = 0;
        int right = list.size() - 1;

        while (left < right){
            if (!Objects.equals(list.get(left), list.get(right))) return false;
            left ++;
            right --;
        }
        return true;
    }


    /**
     * 141. 环形链表
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode pre = head;
        ListNode next = head.next;

        // 判定条件是相同
/*        while (pre != next ){
            if (next == null || next.next == null) return false;
            pre = pre.next;
            next = next.next.next;
        }
        return true;*/

        // 循环判定条件是为空
        while (next != null && next.next != null){
            if (pre == next) return true;
            pre = pre.next;
            next = next.next.next;
        }
        return false;
    }

    /**
     * 142. 环形链表 II
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head.next;
        ListNode fast = head.next.next;
/*        while (fast != null && fast.next != null){
            if (slow == fast) break;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null || fast.next == null) return null;
        fast = head;

        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;*/

        while (slow != fast){
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;

        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * 21. 合并两个有序链表
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode pre = head;

        while (list1 != null && list2 != null){
            if (list1.val < list2.val){
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;

        }
        head.next = list1 != null ? list1: list2;
        return pre.next;
    }

    /**
     * 2. 两数相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n = 0;
        ListNode head = l1;
        while (l1.next != null && l2.next != null){
            int val = l1.val + l2.val + n;
            l1.val = val % 10;
            n = val / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        int v = l1.val + l2.val + n;
        l1.val = v % 10 ;
        n = v / 10;
        l1.next = l1.next != null? l1.next: l2.next;

        while (n > 0 && l1.next != null){
            int val = l1.next.val + n;
            l1.next.val = val % 10;
            n = val / 10;
            l1 = l1.next;
        }
        if (n >= 1) l1.next = new ListNode(n);

        return head;
    }

    /**
     * 两数相加
     * 简单版本
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode pre = head;

        int carry = 0;

        while (l1 != null || l2 != null || carry != 0){
            int val1 = l1 != null? l1.val: 0;
            int val2 = l2 != null? l2.val: 0;
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            head.next = new ListNode();
            head.next.val = sum % 10;
            head = head.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return pre.next;

    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0, head);
        ListNode fast = pre;
        ListNode slow = pre;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return pre.next;
    }


    /**
     * 24. 两两交换链表中的节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
//        ListNode pre = new ListNode(0, head);
//        ListNode prest = pre;
//
//        while (pre.next != null  && pre.next.next != null){
//            ListNode temp = pre.next;
//            pre.next = temp.next;
//            temp.next = pre.next.next;
//            pre.next.next = temp;
//            pre = pre.next.next;
//        }
//        return  prest.next;
        if (head == null || head.next == null) return head;
        ListNode temp = head.next;
        head.next = swapPairs(temp.next);
        temp.next = head;
        return temp;
    }


    /**
     * 148. 排序链表
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // 冒泡
        ListNode pre = new ListNode(0, head);
        ListNode node = head;
        while (node == null){
            ListNode n = node;
            while (n.next != null){
                if (n.val < n.next.val){
                    ListNode temp = n.next.next;
                    n.next.next = n;
                    pre.next = n.next;
                    n.next = temp;

                    pre = pre.next;
                    n = pre.next;
                }
            }
            node = node.next;
        }
        return head;
    }


    public static ListNode ArrayToListNode(int[] array){
        ListNode head = new ListNode(array[0]);
        ListNode pre = head;
        for (int i = 1; i < array.length; i++) {
            pre.next = new ListNode(array[i]);
            pre = pre.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = ArrayToListNode(new int[]{3, 7});
        ListNode l2 = ArrayToListNode(new int[]{9, 2});
        main main = new main();
        main.addTwoNumbers2(l1, l2);
    }
}
