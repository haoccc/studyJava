package hot100.LinkList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public static void main(String[] args) {
        ListNode listNode2 = new ListNode(2, null);
        ListNode listNode1 = new ListNode(1, listNode2);

        hasCycle(listNode1);
    }
}
