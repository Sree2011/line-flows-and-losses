% This program calculates lineflows and line losses of a power system by taking line voltages, currents,
% and Bus Admittance Matrix as input from the user.

% This script calculates lineflows and line losses
% of a power system by taking line voltages, currents,
% and Bus Admittance Matrix as input from the user.

fprintf('Enter the number of buses: ');
n = input('');  % Prompt the user for input

% Initialize matrices
S = zeros(n, n);  % No output
V = zeros(n, n);  % No output
I = zeros(n, n);  % No output
y = zeros(n, n);  % No output

% Take voltages and currents as input
for i = 1:n
    V(i, i) = input(sprintf('Enter the voltage at bus %d: ', i));  % No output
    I(i, i) = input(sprintf('Enter the current at bus %d: ', i));  % No output
end

% Get the choice of input type (impedance or admittance)
choice = input('Enter 1 for impedance and 2 for admittance: ');  % No output

if choice == 1
    % Calculate Admittance
    for i = 1:n
        for j = 1:n
            if i ~= j
                yij = input(sprintf('Enter the impedance between bus %d and %d: ', i, j));  % No output
                y(i, j) = 1 / yij;  % No output
            end
        end
    end
elseif choice == 2
    % Take Admittance as input
    for i = 1:n
        for j = 1:n
            if i ~= j
                y(i, j) = input(sprintf('Enter the admittance between bus %d and %d: ', i, j));  % No output
            end
        end
    end
end

% Calculate voltages
for i = 1:n
    for j = 1:n
        if i ~= j
            V(i, j) = V(i, i) - V(j, j);  % No output
            V(j, i) = V(j, j) - V(i, i);  % No output
        end
    end
end

% Calculate currents
for i = 1:n
    for j = 1:n
        if i ~= j
            I(i, j) = y(i, j) * (V(i, i) - V(j, j));  % No output
            I(j, i) = y(j, i) * (V(j, j) - V(i, i));  % No output
        end
    end
end

% Calculate Lineflows and line losses
for i = 1:n
    for j = 1:n
        if i ~= j
            % Line Flows
            S(i, j) = V(i, j) * conj(I(i, j));  % No output
            S(j, i) = V(j, i) * conj(I(j, i));  % No output
            
            % Line losses
            SL(i, j) = S(i, j) + S(j, i);  % No output
        end
    end
end

% Display results (Optional, remove if you want no output at all)
disp('The initialized matrix S is:')
disp(S)

