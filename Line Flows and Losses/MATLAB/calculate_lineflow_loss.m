function [S,SL] = calculate_lineflow_loss(n,V,I,y)
    % Calculate voltages
    for i = 1:n
        for j = 1:n
            if i~= j
                V(i, j) = V(i, i) - V(j, j);  % No output
                V(j, i) = V(j, j) - V(i, i);  % No output
            end
        end
    end

    % Calculate currents
    for i = 1:n
        for j = 1:n
            if i~= j
                I(i, j) = y(i, j) * (V(i, i) - V(j, j));  % No output
                I(j, i) = y(j, i) * (V(j, j) - V(i, i));  % No output
            end
        end
    end
    % Calculate Lineflows and line losses
    for i = 1:n
        for j = 1:n
                % Line Flows
                S(i, j) = V(i, j) * conj(I(i, j));  % No output
                S(j, i) = V(j, i) * conj(I(j, i));  % No output
                
                % Line losses
                SL(i, j) = S(i, j) + S(j, i);  % No output
        end
    end
end