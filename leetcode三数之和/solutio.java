class Solution {
    /*
    先将数组排序
    数组指针指向第一个元素为k（0，length-2）， i=k+1，j=length-1；
    k指针移动规则：在定义域内；向下移动的数不与当前数相同；所指向的数小于等于0
    i指针移动规则：在定义域内；sum小于0向后移动；向下移动的数不与当前数相同；
    j指针移动规则：在定义域内；sum大于0向前移动；向下移动的数不与当前数相同;
    */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int k = 0;
        for (; k < nums.length - 2; k++) {
            if(nums[k]>0) break;//nums[k]=0，数组增序，那么后面全部大于0
            if(k>0&&nums[k]==nums[k-1]) continue;//移动k指针后与前面数字相同，查找的结果也会相同
            int i = k + 1;
            int j = nums.length - 1;
            while(i<j)
            {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while(i<j&&nums[j]==nums[--j]);
                } else if (sum < 0) while(i<j&&nums[i]==nums[++i]);
                else while(i<j&&nums[j]==nums[--j]);

                /*使用while(i<j&&nums[j]==nums[--j])的原因是，我们需要移动数组的指针，并且需要移动到与当前数组指针不一样的位置去
                那么就需要一直移动移动到（i==j或者nums[j]!=nums[--j]的地方才不停止移动）
                比如有大量的重复元素数组【-5，-3，-1，-1，0，0，0，2，2，2，2，4】
                当数组指针分别指在-5，第一个0，4的时候，需要将i指针移动到2，那么就要一直往后移动
                 */
            }
        }
        return ans;
    }
}