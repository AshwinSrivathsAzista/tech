compare setps with ppt and so it of other ds



- log reduces right skew
- exp increases right skew

- outliers: -
    - Zscore > +3 or < -3
    - outside 3/2 IQR

- ordinary least square method OLS minimizes sum of least squared errors sigma e square
- RMSE = sqrt((sigma(e**2)) / n)



- Regularization - ModelsThese models are designed to prevent overfitting by adding a penalty term to the cost function.
    - Ridge ($L2$): Adds a penalty equal to the square of the magnitude of coefficients. It shrinks coefficients toward zero but never makes them exactly zero. It's great when most features are useful.
    - Lasso ($L1$): Adds a penalty equal to the absolute value of coefficients. It can shrink some coefficients to exactly zero, effectively performing feature selection.
    - ElasticNet: A middle ground that combines both $L1$ and $L2$ penalties. The l1_ratio parameter (set to 0.01 in your prompt) determines the mix; here, it is heavily weighted toward Ridge.

