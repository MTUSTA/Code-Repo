`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   19:50:06 11/30/2017
// Design Name:   four_bit_adder
// Module Name:   C:/Users/MEHMET TAHA USTA/myproject/tb_four_bit_adder.v
// Project Name:  myproject
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: four_bit_adder
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module tb_four_bit_adder;

	// Inputs
	reg [3:0] A;
	reg [3:0] B;
	reg Cin;

	// Outputs
	wire [3:0] Sum;
	wire Cout;

	// Instantiate the Unit Under Test (UUT)
	four_bit_adder uut (
		.A(A), 
		.B(B), 
		.Cin(Cin), 
		.Sum(Sum), 
		.Cout(Cout)
	);

	initial begin
		// Initialize Inputs
		A = 4'b0000;B=4'b0000;Cin=0;
		#50 A = 4'b0001;B=4'b0001;Cin=0;
		#50 A = 4'b0011;B=4'b0011;Cin=0;
		#50 A = 4'b0111;B=4'b0111;Cin=0;
		#50 A = 4'b1111;B=4'b1111;Cin=0;
		#50 A = 4'b0001;B=4'b0001;Cin=1;
		#50 A = 4'b0011;B=4'b0011;Cin=1;
		#50 A = 4'b0111;B=4'b0111;Cin=1;
		#50 A = 4'b1111;B=4'b1111;Cin=1;

		// Wait 100 ns for global reset to finish
		#100;
        
		// Add stimulus here

	end
      
endmodule

