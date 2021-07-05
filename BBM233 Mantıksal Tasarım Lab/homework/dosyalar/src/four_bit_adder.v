`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:49:25 11/30/2017 
// Design Name: 
// Module Name:    four_bit_adder 
// Project Name: 
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////
module four_bit_adder(input [3:0] A,B,input Cin,output [3:0] Sum,output Cout);

wire tasiyici0;
wire tasiyici1;
wire tasiyici2;

one_bit_adder FA1(
	.A(A[0]),
	.B(B[0]),
	.Cin(Cin),
	.Sum(Sum[0]),
	.Cout(tasiyici0)
);

one_bit_adder FA2(
	.A(A[1]),
	.B(B[1]),
	.Cin(tasiyici0),
	.Sum(Sum[1]),
	.Cout(tasiyici1)
);

one_bit_adder FA3(
	.A(A[2]),
	.B(B[2]),
	.Cin(tasiyici1),
	.Sum(Sum[2]),
	.Cout(tasiyici2)
);

one_bit_adder FA4(
	.A(A[3]),
	.B(B[3]),
	.Cin(tasiyici2),
	.Sum(Sum[3]),
	.Cout(Cout)
);

endmodule
