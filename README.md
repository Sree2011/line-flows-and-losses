# line-flows-and-line-losses
Finding line flows and line losses in a power system

![A sample bus system](https://github.com/user-attachments/assets/5e8a3816-cd88-49b9-abd2-7cedb479baed)

Consider a power system like in this diagram.

Current from bus i to bus j is calculated as,

$$
I_{ij} = y_{ij}(V_i - V_j)+y_{i0} V_i
$$

Current from bus j to bus i is calculated as,

$$
I_{ji} = y_{ji}(V_j - V_i)+y_{j0} V_i
$$

The lineflows:

$$
S_{ij} = V_{ij}I_{ij}^{*}
$$
$$
S_{ji} = V_{ji}I_{ji}^{*}
$$

Line losses:

$$
S_{L_{ij}} = S_{ij} + S_{ji}
$$


## References

[1] Hadi Saadat, *Power System Analysis*, Mc-Graw Hill Education,2010
