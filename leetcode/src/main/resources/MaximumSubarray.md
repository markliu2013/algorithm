### Analysis The Problem

For the sake of understanding the problem, we try to describe in math, we want to find a sub-array of A<sub>1...n</sub> which

$$ \sum_{k=i \ , \ i \geq 1 }^{j \ , \ i \leq j \leq n}A_k $$

is the maximum.


#### Approach #1 Brute Force [Time Limit Exceeded]

**Intuition**

The most straightforward approach, loop the array and find every pair of

$$ (i, j) \ 0 \leq i \leq j < n$$

we get every sum from i to j, and choose the one which is maximum.

**Java**

```java
public int maxSubArray(int[] nums) {
  int maxSubArray = Integer.MIN_VALUE;
  for (int i = 0; i < nums.length; i++) {
    for (int j = i; j < nums.length; j++) {
      int sum = 0;
      for (int k = i; k <= j; k++) {
        sum += nums[k];
      }
      maxSubArray = Math.max(maxSubArray, sum);
    }
  }
  return maxSubArray;
}
```

**Complexity Analysis**

* Time complexity : $$O(n^3)$$

we have

$$ \frac{n(n+1)}{2} $$

combinations to pair i and j, then loop from i to j.

#### Approach #2 Optimize Brute Force [Accepted]

**Intuition**

We loop from i to j every time, is there a way to optimize? for a specified i, we can sum (i, i), (i, i+1), (i, i+2)...(i, n-1)

we can use the previous sum

$$ \sum_{k=i}^{j}A_k $$

to get next sum.

$$
\sum_{k=i}^{j+1}A_k = A_{j+1} + \sum_{k=i}^{j}A_k
$$

Then we don't need loop from i to j every time, time saved.

**Java**

```java
public int maxSubArray(int[] nums) {
  int maxSubArray = Integer.MIN_VALUE;
  for (int i = 0; i < nums.length; i++) {
    int sum = 0;
    for (int j = i; j < nums.length; j++) {
      sum += nums[j];
      maxSubArray = Math.max(sum, maxSubArray);
    }
  }
  return maxSubArray;
}
```

#### Approach #3 Divide and Conquer [Accepted]

**Algorithm**

1. Divide the array from middle, assume you already know the left side max sum is max_left and right side is max_right

2. The whole array has 3 conditions, max_left, max_right or a span contains mid.

3. The key is how to calculate the span contains mid, we start from mid to left, get the maximum of left side, then start from mid to right, get the maximum of right side. the maxMid is left and right maximum.

**Java**

```java
public int maxSubArray(int[] nums) {
  if (nums.length == 1) {
    return nums[0];
  }
  int mid = nums.length / 2;
  int[] leftNums = Arrays.copyOfRange(nums, 0, mid);
  int[] rightNums = Arrays.copyOfRange(nums, mid, nums.length);
  int maxLeft = maxSubArray(leftNums);
  int maxRight = maxSubArray(rightNums);
  //accross mid
  int midMaxLeft = Integer.MIN_VALUE;
  int midMaxRight = Integer.MIN_VALUE;
  int sum = 0;
  for (int i = mid-1; i >= 0; i--) {
    sum += nums[i];
    midMaxLeft = Math.max(midMaxLeft, sum);
  }
  sum = 0;
  for (int i = mid; i < nums.length; i++) {
    sum += nums[i];
    midMaxRight = Math.max(midMaxRight, sum);
  }
  int maxMid = midMaxLeft + midMaxRight;
  return Math.max(Math.max(maxLeft, maxRight), maxMid);
}
```

**Complexity Analysis**

* Time complexity : $$ \Theta(nlgn) $$

#### Approach #4 Dynamic Programming [Accepted]

Imagine you know A[i...j]'s max sub-array, then A[i...j+1]'s max sub-array is A[i...j]'s max sub-array, or one of A[m...j+1], i<=m<=j+1, end by A<sub>j+1</sub>

we can loop from A<sub>j+1</sub> to left side find the maximum, but it would be too slow.

Now the key point is how to find the maximum sub-array end by A<sub>j+1</sub>, actually when we loop the array from i, we can record the maximum sub-array end by i, assume

$$
sum_j = \sum_{t=m}^{j}A_t
$$

is the maximum sub-array end by A<sub>j</sub> , we can get

$$
sum_j = \begin{cases}
\sum_{t=m}^{j}A_t+A_{j+1} & \sum_{t=m}^{j}A_t+A_{j+1}>A_{j+1}\\
A_{j+1} & \sum_{t=m}^{j}A_t+A_{j+1} \leq A_{j+1}
\end{cases}
$$

So we resolved the problem how to find the maximum sub-array end by A<sub>j+1</sub>

The maximum sub-array to A<sub>j+1</sub>(maybe not end by A<sub>j+1</sub>), we still need figure it out.

We can use a max array to record maximum sub-array to A<sub>j+1</sub>  , assume max<sub>j</sub> is the maximum sub-array to A<sub>j</sub> , then compare with the maximum sub-array end by A<sub>j+1</sub>

$$
max_{j+1} = \begin{cases}
max_{j} & max_{j}>sum_{j+1}\\
sum_{j+1} & max_{j} \leq sum_{j+1}
\end{cases}
$$

**Java**

```java
public int maxSubArray(int[] nums) {
  int[] max = new int[nums.length];
  int[] sum = new int[nums.length];
  sum[0] = nums[0];
  max[0] = nums[0];
  for (int i = 1; i < nums.length; i++) {
    sum[i] = Math.max(sum[i-1]+nums[i], nums[i]);
    max[i] = Math.max(sum[i], max[i-1]);
  }
  return max[nums.length-1];
}
```

**Complexity Analysis**

* Time complexity : $$ O(n) $$

* Space complexity : $$ O(2n) $$

#### Approach #5 Optimize Dynamic Programming [Accepted]

**Intuition**
We still can optimize the space complexity, we don't need store every i's maximum, we can only store current maximum.

**Java**

```java
public int maxSubArray(int[] nums) {
  int curSum = nums[0];
  int maxSum = nums[0];
  for (int i = 1; i < nums.length; i++) {
    /* end by nums[i]'s maximum */
    if (curSum < 0) {
      curSum = nums[i];
    } else {
      curSum += nums[i];
    }
    /* to i's maximum */
    maxSum = Math.max(maxSum, curSum);
  }
  return maxSum;
}
```

**Complexity Analysis**

* Time complexity : $$ O(n) $$

* Space complexity : $$ O(1) $$
