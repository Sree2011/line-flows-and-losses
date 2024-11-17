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
% Calculate voltages
for i = 1:n
    for j = 1:n
        if i~= j
            V(i, j) = V(i, i) - V(j, j);  % No output
            V(j, i) = V(j, j) - V(i, i);  % No output
        end
    end
end
disp(V)
% Calculate currents
for i = 1:n
    for j = 1:n
        if i~= j
            I(i, j) = y(i, j) * (V(i, i) - V(j, j));  % No output
            I(j, i) = y(j, i) * (V(j, j) - V(i, i));  % No output
        end
    end
end
disp(I)
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

% Display results (Optional, remove if you want no output at all)
data = {};  % Initialize an empty cell array for storing data

for i = 1:n
    for j = 1:n
        data{end+1, 1} = sprintf('%d-%d', i, j);  % Bus Pair
        data{end, 2} = sprintf('%.4f + %.4fj', real(V(i, j)), imag(V(i, j)));  % Voltage
        data{end, 3} = sprintf('%.4f + %.4fj', real(I(i, j)), imag(I(i, j)));  % Current
        data{end, 4} = sprintf('%.4f + %.4fj', real(S(i, j)), imag(S(i, j)));  % Line Flow
        data{end, 5} = sprintf('%.4f + %.4fj', real(SL(i, j)), imag(SL(i, j)));  % Line Loss
    end
end

% Display the table with headers
headers = {'Bus Pair', 'Voltage', 'Current', 'Line Flow', 'Line Loss'};
result = [headers;data];


% Print the table header
fprintf('%-10s %-20s %-20s %-20s %-20s\n', headers{1},headers{2},headers{3},headers{4},headers{5});
fprintf('%s\n', repmat('-', 1, 90)); % Print a separator line
% Print the table rows
for i = 1:size(data, 1)
    fprintf('%-10s %-20s %-20s %-20s %-20s\n', data{i, 1}, data{i, 2}, data{i, 3}, data{i, 4}, data{i, 5});
end

