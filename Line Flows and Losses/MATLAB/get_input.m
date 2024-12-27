function [V,I,y]  = get_input(n,V,I,y)
    
    % Take voltages and currents as input
    for i = 1:n
        V(i, i) = input(sprintf('Enter the voltage at bus %d: ', i));  % No output
        I(i, i) = input(sprintf('Enter the current at bus %d: ', i));  % No output
    end

    % Get the choice of input type (impedance or admittance)
    choice = input('Enter 1 for impedance and 2 for admittance: ');  % No output

    if(choice == 1)
        % Calculate Admittance
        for i = 1:n
            for j = 1:n
                yij = input(sprintf('Enter the impedance between bus %d and %d: ', i, j));  % No output
                y(i, j) = 1 / yij;  % No output
            end
        end
    elseif(choice == 2)
        % Take Admittance as input
        for i = 1:n
            for j = 1:n
                y(i, j) = input(sprintf('Enter the admittance between bus %d and %d: ', i, j));  % No output
            end
        end
    end
end