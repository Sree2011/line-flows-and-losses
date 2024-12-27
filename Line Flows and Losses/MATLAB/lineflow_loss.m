% This program calculates lineflows and line losses of a power system by taking line voltages, currents,
% and Line admittances as input from the user.

fprintf('Enter the number of buses: ');
n = input('');  % Prompt the user for input

% Initialize matrices
S = zeros(n, n);  % No output
V = zeros(n, n);  % No output
I = zeros(n, n);  % No output
y = zeros(n, n);  % No output

% Get input from the user
% \link get_input.m get_input \endlink
[V,I,y] = get_input(n,V,I,y);

% Calculate lineflows and line losses
% \link calculate_lineflow_loss.m calculate_lineflow_loss \endlink
[S,SL] = calculate_lineflow_loss(n,V,I,y);  % Calculate lineflows and line losses

% Display the output
% \link display_output.m display_output \endlink
display_output(n,V,I,S,SL);  % Display the output