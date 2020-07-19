package algorithm.week01;

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                count++;
            } else {
                nums[i-count] = nums[i];
            }
        }
        return nums.length-count;
    }

    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                if (++j != i) {
                    nums[j] = nums[i];
                }
            }
        }
        return j+1;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        new RemoveDuplicates().removeDuplicates(nums);
    }
}
