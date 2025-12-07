CDF (Cumulative Distribution Function): Probability of everything to the left of a point.
SF (Survival Function): Probability of everything to the right of a point.

pvalue probability of supporting H0
pvalue is area calculated on the rejection side




# linear algebra

## matrices
- a = np.array([[1,2,3], [4,15,6], [2,8,9]])
- a * b => not matrix multiplication and pointwise multiplication
- a @ b.T => matrix multiplication
- np.linalg.solve([[2, 3, 4], [4, 6, 0], [0, 1, 9]], [10, 12, 18]) => solving linear equations 
    - convert the equ to matrices
    - ensure that the equations are written in order (var order)
- aInv = np.linalg.inv(a)
- np.linalg.norm([11,4]) # distance from origin to point (11,4)
- 