% /// \brief Display the output results in a tabular format.
% /// \param n The number of buses in the system.
% /// \param V The voltage matrix.
% /// \param I The current matrix.
% /// \param S The line flow matrix.
% /// \param SL The line loss matrix.
function display_output(n,V,I,S,SL)
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
end